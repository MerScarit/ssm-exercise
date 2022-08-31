package com.scarit.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.scarit.configuration.CustomObjectMapper;
import com.scarit.constant.Constants;
import com.scarit.core.RedisTemplate;
import com.scarit.entity.ExerciseMenu;
import com.scarit.entity.ExerciseRole;
import com.scarit.entity.ExerciseUser;
import com.scarit.dao.ExerciseUserDao;
import com.scarit.entity.LoginUser;
import com.scarit.exception.PasswordIncorrectException;
import com.scarit.exception.UserNotFoundException;
import com.scarit.service.ExerciseUserService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户信息表(ExerciseUser)表服务实现类
 *
 * @author makejava
 * @since 2022-08-26 16:44:04
 */
@Service("exerciseUserService")
@Slf4j
public class ExerciseUserServiceImpl implements ExerciseUserService {
    @Resource
    private ExerciseUserDao exerciseUserDao;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private CustomObjectMapper objectMapper;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public ExerciseUser queryById(Long userId) {
        return this.exerciseUserDao.queryById(userId);
    }

    /**
     * 分页查询
     *
     * @param exerciseUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ExerciseUser> queryByPage(ExerciseUser exerciseUser, PageRequest pageRequest) {
        long total = this.exerciseUserDao.count(exerciseUser);
        return new PageImpl<>(this.exerciseUserDao.queryAllByLimit(exerciseUser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param exerciseUser 实例对象
     * @return 实例对象
     */
    @Override
    public ExerciseUser insert(ExerciseUser exerciseUser) {
        this.exerciseUserDao.insert(exerciseUser);
        return exerciseUser;
    }

    /**
     * 修改数据
     *
     * @param exerciseUser 实例对象
     * @return 实例对象
     */
    @Override
    public ExerciseUser update(ExerciseUser exerciseUser) {
        this.exerciseUserDao.update(exerciseUser);
        return this.queryById(exerciseUser.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.exerciseUserDao.deleteById(userId) > 0;
    }

    @Override
    public LoginUser login(String userName, String password) {

        //1.登录，使用用户名查询用户，没有查询到就说明没有该账户
        ExerciseUser exerciseUser = exerciseUserDao.queryByUserName(userName);
        if(exerciseUser == null) throw new UserNotFoundException("执行登陆操作： [" + userName +  "] 该用户不存在");
        //2.如果查到用户，则比较密码，如果密码不正确则登陆失败
        if(!password.equals(exerciseUser.getPassword())){
            log.info("执行登陆操作： [" + userName +  "] 输入密码错误");
            throw new PasswordIncorrectException("执行登陆操作： [" + userName +  "] 输入密码错误");
        }

        //3.如果验证成功
        //（1）生成token
        String token = UUID.randomUUID().toString();

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));

        //通过ip获取其所属地址
        ResponseEntity<String> result = restTemplate.getForEntity("https://whois.pconline.com.cn/ipJson.jsp?ip=126.255.144.12&json=true", String.class);
        String body = result.getBody();
        Map<String,String> map= null;
        try {
            map = objectMapper.readValue(body, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String location = map.get("addr") + map.get("pro") + map.get("city") + map.get("region");

        //（2）封装一个登录用户ExerciseUser，保存在Redis
        LoginUser loginUser = LoginUser.builder()
                .userId(exerciseUser.getUserId())
                .token(token)
                .ipaddr(request.getRemoteAddr())
                .loginTime(new Date())
                .os(userAgent.getOperatingSystem().getName())
                .browser(userAgent.getBrowser().getName())
                .loginLocation(location)
                .exerciseUser(exerciseUser)
                .build();

        //对key进行处理 token：username：xuuid
        //1.根据用户名生成key前缀token：username：
        String keyPrefix = Constants.TOKEN_PREFIX + userName + ":";
        //2.根据生成的key的前缀进行token：username：进行查询
        Set<String> keys = redisTemplate.keys(keyPrefix + "*");
        //3.删除原来的数据
        keys.forEach(key -> redisTemplate.remove(key));
        //4.把新的数据加入redis
        redisTemplate.setObject(keyPrefix+token,loginUser,Constants.TOKEN_TIME);

        return loginUser;
    }

    @Override
    public void logout() {

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        //获取首部信息的token
        String token = request.getHeader(Constants.HEAD_AUTHORIZATION);
        //删除redis中的token，user这些数据
        redisTemplate.remove(Constants.TOKEN_PREFIX + token);
    }

    @Override
    public HashMap<String,List<String>> getInfo() {
        //1.获取当前登录的对象
        LoginUser loginUser = getLoginUser();
        //2.查询当前用户的角色和权限
        ExerciseUser info = exerciseUserDao.getInfo(loginUser.getUserId());
        //3.处理权限和角色的相关信息
        //（1）roles:token :[admin,xxx,yyy]   perms:token:[system:user:add, system:user:update]
        List<String> roleTags = info.getExerciseRoles().stream().map(ExerciseRole::getRoleTag).collect(Collectors.toList());
        redisTemplate.setObject(Constants.ROLE_PREFIX + loginUser.getToken(),roleTags,Constants.TOKEN_TIME);
        List<String> perms = new ArrayList<>();
        //
        info.getExerciseRoles().stream().map(ExerciseRole::getExerciseMenus).forEach(menus ->{
            perms.addAll(menus.stream().map(ExerciseMenu::getPerms).collect(Collectors.toList()));
        });
        redisTemplate.setObject(Constants.PERMS_PREFIX + loginUser.getToken(), perms,Constants.TOKEN_TIME);

        //整合数据
        HashMap<String,List<String>> data = new HashMap<>();
        data.put("roles",roleTags);
        data.put("perms",perms);
        return data;
    }

    private LoginUser getLoginUser(){

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
            return redisTemplate.getObject(tokenKey, new TypeReference<>() {
            });
    }
}
