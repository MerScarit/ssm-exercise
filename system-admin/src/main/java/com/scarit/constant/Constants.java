package com.scarit.constant;

public class Constants {

    //统一的token前缀
    public static final String TOKEN_PREFIX = "token:";
    //权限请求头
    public static final String HEAD_AUTHORIZATION = "Authorization";
    //token的过期时间
    public static final Long TOKEN_TIME = 30 * 60L;
    //角色前缀
    public static final String ROLE_PREFIX = "roles:";
    //权限前缀
    public static final String PERMS_PREFIX = "prem:";
    //防止重复提交的前缀
    public static final String REPEAT_SUBMIT_KEY = "repeat";
}
