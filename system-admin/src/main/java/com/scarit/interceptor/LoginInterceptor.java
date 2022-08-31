package com.scarit.interceptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scarit.configuration.CustomObjectMapper;
import com.scarit.constant.Constants;
import com.scarit.core.RedisTemplate;
import com.scarit.entity.LoginUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CustomObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //2.如果不是白名单的请求，检测
        //判断有没有Authoriza这个请求头，拿到首部信息Authoriza的值
        ResponseEntity<String> res = ResponseEntity.status(401).body("Bad Credentials");
//        String token = request.getHeader(Constants.HEAD_AUTHORIZATION);
//
//        if(token == null) {
//            response.setStatus(401);
//            response.getWriter().write(objectMapper.writeValueAsString(res));
//            return false;
//        }
//        // String tokenKey = Constants.TOKEN_PREFIX + request.getHeader("username") + ":" +token;
//        Set<String> keys = redisTemplate.keys(Constants.TOKEN_PREFIX + "*" + token);
//        if(keys == null || keys.size() ==0){
//            response.setStatus(401);
//            response.getWriter().write(objectMapper.writeValueAsString(res));
//            return false;
//        }
//        String tokenKey = (String)keys.toArray()[0];
//        //3.使用token去redis中查看，有没有对应的loginUser
//            LoginUser loginUser = redisTemplate.getObject(tokenKey, new TypeReference<>() {
//            });
//            if (loginUser == null) {
//                response.setStatus(401);
//                response.getWriter().write(objectMapper.writeValueAsString(res));
//                return false;
//            }
//            //给token续命
//            redisTemplate.expire(Constants.TOKEN_PREFIX +token,Constants.TOKEN_TIME);
        return true;
    }
}
