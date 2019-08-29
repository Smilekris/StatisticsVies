package com.smile.monkeyserver.listener;

import com.smile.monkeyapi.constants.RedisConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * @author kris
 */
//@WebListener
public class MonitorHttpSessionListener implements HttpSessionListener {

    public final static Logger LOG = LoggerFactory.getLogger(MonitorHttpSessionListener.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        LOG.info("http seesion listener->"+session);
        LOG.info("session created");
        Boolean ac_b = redisTemplate.hasKey(RedisConstants.ACTIVE_NUM);
        if(ac_b){
            int activeNum = (int)redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
            activeNum = increase(activeNum);
            redisTemplate.opsForValue().set(RedisConstants.ACTIVE_NUM,activeNum);
        }else{
            redisTemplate.opsForValue().set(RedisConstants.ACTIVE_NUM,1);
        }
        LOG.info("session created active"+(int)redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        LOG.info(""+session);
        LOG.info("session destroyed");
        Boolean ac_b = redisTemplate.hasKey(RedisConstants.ACTIVE_NUM);
        if(ac_b){
            int activeNum = (int)redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
            activeNum = decrease(activeNum);
            redisTemplate.opsForValue().set(RedisConstants.ACTIVE_NUM,activeNum);
        }else{
            redisTemplate.opsForValue().set(RedisConstants.ACTIVE_NUM,0);
        }
        LOG.info("session created destory"+(int)redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM));
    }

    public int increase(int activeNum){
        return ++activeNum;
    }

    public int decrease(int activeNum){
        return --activeNum;
    }
}
