package com.smile.monkeyserver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁-
 *
 * @ClassName DistributedRedisLock
 * @Author kris
 * @Date 2019/8/9
 **/
public class DistributedRedisLock {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedRedisLock.class);

    /**
     * 分布式锁的value,后面用于解锁
     */
    private String redisValue;

    /**
     * 分布式锁的key,后面用于解锁
     */
    private String redisKey;

    /**
     * 最大等待时间，防止线程饥饿
     */
    private int maxAwaitTimeSeconds=10;

    @Autowired
    private RedisTemplate redisTemplate;


    public int getMaxAwaitTimeSeconds() {
        return maxAwaitTimeSeconds;
    }

    public void setMaxAwaitTimeSeconds(int maxAwaitTimeSeconds) {
        this.maxAwaitTimeSeconds = maxAwaitTimeSeconds;
    }

    public String getRedisValue() {
        return redisValue;
    }

    public void setRedisValue(String redisValue) {
        this.redisValue = redisValue;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    /**
     * 对于用户而言，发出请求不应该被丢弃，因此锁失败应该后面继续尝试
     * @param key rediskey
     * @param value 值
     * @return 是否锁成功
     */
    public boolean lock(String key, String value) {
        int timeOutSeconds = getMaxAwaitTimeSeconds();
        if(timeOutSeconds > 0){
            //redis的操作都是原子操作，對於同一个key来说，同一时间，只有一个能执行成功
            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, value, 30, TimeUnit.SECONDS);
            if(aBoolean){
                this.setRedisValue(value);
                LOGGER.info("First time to execute program success, time is {}",DateUtil.getCurrentTimeStr());
            }
        }
        return true;
    }


    public boolean unlock(){
        Object o = redisTemplate.opsForValue().get(this.getRedisKey());
        if(o instanceof String){
            String value = (String) o;
            if (this.getRedisValue().equals(value)){
                redisTemplate.delete(this.getRedisKey());
                return true;
            }
        }
        return false;
    }
}
