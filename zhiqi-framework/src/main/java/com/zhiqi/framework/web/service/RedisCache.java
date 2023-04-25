package com.zhiqi.framework.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author RyuJung
 * @since 2023/4/19-12:32
 */
@SuppressWarnings(value = {"rawtypes", "unchecked"})
@Service
public class RedisCache {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(String key, Class<T> cls) {
        Object valueObject = redisTemplate.opsForValue().get(key);
        if (valueObject != null) {
            return cls.cast(valueObject);
        }
        return null;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key        缓存的键值
     * @param value      缓存的值
     * @param expireTime 时间
     * @param timeUnit   时间颗粒度
     */
    public <T> void setCacheObject(String key, T value, int expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
    }

    /**
     * 删除单个对象
     *
     * @param redisKey
     */
    public boolean deleteObject(String redisKey) {
        return redisTemplate.delete(redisKey);
    }

}
