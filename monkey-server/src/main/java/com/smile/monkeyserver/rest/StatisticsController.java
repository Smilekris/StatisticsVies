package com.smile.monkeyserver.rest;

import com.smile.monkeyapi.constants.RedisConstants;
import com.smile.monkeyapi.enitity.ResponseResult;
import com.smile.monkeyserver.amqp.RabbitProducer;
import com.smile.monkeyserver.exception.MonkeyException;
import com.smile.monkeyserver.service.MockAccluateService;
import com.smile.monkeyserver.service.VistorService;
import com.smile.monkeyserver.util.DistributedRedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kris
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private VistorService vistorService;
    @Autowired
    private RabbitProducer rabbitProducer;
    @Autowired
    private MockAccluateService mockAccluateService;

    @RequestMapping("/surf")
    public ResponseResult add(HttpServletRequest request) {
        int surfingNum = (int) redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
        String result = "当前共有" + surfingNum + "人正在看";
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(result);
    }

    @RequestMapping("/test")
    public ResponseResult test(HttpServletRequest request) {
        int surfingNum = (int) redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
        String result = "当前共有" + surfingNum + "人正在看";
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(result);
    }

    @RequestMapping("/sum")
    public ResponseResult sum(HttpServletRequest request) {
        long sum = vistorService.vists();
        String result = "共" + sum + "人次浏览";
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(result);
    }

    @RequestMapping("/test/mq")
    public ResponseResult testMQ(HttpServletRequest request) {
        rabbitProducer.stringSend();
        return ResponseResult.ResultHelper.successInstance().setMsg("ok");
    }

    @RequestMapping("/test/time")
    public ResponseResult testTime(HttpServletRequest request) {
        vistorService.test();
        return ResponseResult.ResultHelper.successInstance().setMsg("ok");
    }

    @RequestMapping("/test/dis-lock")
    public ResponseResult testLock(HttpServletRequest request) {
        String testuser = (String) redisTemplate.opsForValue().get("usertest");
        if (!StringUtils.isEmpty(testuser)) {
            throw new MonkeyException("用户已存在");
        }
        DistributedRedisLock lock = new DistributedRedisLock(redisTemplate);
        try{
            lock.lock("test","cjw");
            mockAccluateService.deductMoney(1,10);
            lock.unlock();
        }catch(MonkeyException e){
            LOGGER.error("test distribute lock ",e);
        }
        return ResponseResult.ResultHelper.successInstance().setMsg("ok");
    }
}
