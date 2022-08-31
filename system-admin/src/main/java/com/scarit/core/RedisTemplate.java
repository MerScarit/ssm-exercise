package com.scarit.core;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scarit.configuration.CustomObjectMapper;
import com.scarit.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
public class RedisTemplate {

    @Resource
    private JedisPool jedisPool;

    @Resource
    private CustomObjectMapper objectMapper;

    //保存字符串类型的数据
    public String set(String key,String value,Long expire){
        Jedis jedis = jedisPool.getResource();
        String returnValue = null;
        try {
            returnValue = jedis.setex(key, expire, value);
        }catch (JedisException jedisException){
            log.error("Redis执行异常", jedisException);
            jedisPool.returnBrokenResource(jedis);
        }finally {
            jedisPool.returnResource(jedis);
        }
        return returnValue;
    }

    public String get(String key){
        Jedis jedis = jedisPool.getResource();
        String returnValue = null;
        try {
            returnValue = jedis.get(key);
        }catch (JedisException jedisException){
            log.error("Redis执行异常", jedisException);
            jedisPool.returnBrokenResource(jedis);
        }finally {
            jedisPool.returnResource(jedis);
        }
        return returnValue;
    }

    public long remove(String ...key){
        Jedis jedis = jedisPool.getResource();
        long del = 0L;
        try {
             del = jedis.del(key);
        }catch (JedisException jedisException){
            log.error("Redis执行异常", jedisException);
            jedisPool.returnBrokenResource(jedis);
        }finally {
            jedisPool.returnResource(jedis);
        }
        return del;
    }

    //将对象以序列化的方式存在Redis，json
    public String setObject(String key,Object value,Long expire){
        Jedis jedis = jedisPool.getResource();
        String returnValue = null;
        try {
            //首先先序列化成JSON
            String jsonValue = objectMapper.writeValueAsString(value);
            if(expire < 0){
                returnValue = jedis.set(key, jsonValue);
            } else{
                returnValue = jedis.setex(key, expire, jsonValue);
            }
        }catch (JedisException | JsonProcessingException exception){
            log.error("Redis执行异常", exception);
            jedisPool.returnBrokenResource(jedis);
        }finally {
            jedisPool.returnResource(jedis);
        }
        return returnValue;
    }

    public <T>T getObject(String key, TypeReference<T> clazz){
        Jedis jedis = jedisPool.getResource();
        T object = null;
        try {
            //从Redis里获取
            String returnValue = jedis.get(key);
            object = objectMapper.readValue(returnValue,clazz);
            //    objectMapper.readValue(returnValue,typeReference);
        }catch (JedisException | JsonProcessingException exception){
            log.error("Redis执行异常", exception);
            jedisPool.returnBrokenResource(jedis);
        }finally {
            jedisPool.returnResource(jedis);
        }
        return object;
    }
    //增加key的时间
    public long expire(String key,Long expire){
        Jedis jedis = jedisPool.getResource();
        Long exp = -1L;
        try {
             exp = jedis.expire(key, expire);
        }catch (JedisException exception){
            log.error("Redis执行异常", exception);
            jedisPool.returnBrokenResource(jedis);
        }finally {
            jedisPool.returnResource(jedis);
        }
        return exp;
    }
    //查询特定的key
    public Set<String> keys(String pattern){
        Jedis jedis = jedisPool.getResource();
        Set<String> keys = null ;
        try {
           keys = jedis.keys(pattern);
        }catch (JedisException exception){
            log.error("Redis执行异常", exception);
            jedisPool.returnBrokenResource(jedis);
        }finally {
            jedisPool.returnResource(jedis);
        }
        return keys;
    }
    }

