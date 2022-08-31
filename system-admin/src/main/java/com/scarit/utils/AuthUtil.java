package com.scarit.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scarit.constant.Constants;
import com.scarit.core.RedisTemplate;
import com.scarit.entity.LoginUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Set;

public class AuthUtil {
    /**
     * 获取当前的登录对象
     * @return
     */
    public static LoginUser getLoginUser(RedisTemplate redisTemplate){

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader(Constants.HEAD_AUTHORIZATION);

        if(token == null) {
            throw new RuntimeException("当前用户未登录！");
        }
        // String tokenKey = Constants.TOKEN_PREFIX + request.getHeader("username") + ":" +token;
        Set<String> keys = redisTemplate.keys(Constants.TOKEN_PREFIX + "*" + token);
        if(keys == null || keys.size() ==0){
            throw new RuntimeException("当前用户未登录！");

        }
        String tokenKey = (String)keys.toArray()[0];
        //3.使用token去redis中查看，有没有对应的loginUser
        return redisTemplate.getObject(tokenKey, new TypeReference<>() {});
    }
}
