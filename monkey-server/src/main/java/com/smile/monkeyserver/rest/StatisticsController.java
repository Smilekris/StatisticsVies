package com.smile.monkeyserver.rest;

import com.smile.monkeyapi.constants.RedisConstants;
import com.smile.monkeyapi.enitity.ResponseResult;
import com.smile.monkeyserver.amqp.RabbitProducer;
import com.smile.monkeyserver.service.VistorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kris
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private VistorService vistorService;
    @Autowired
    private RabbitProducer rabbitProducer;

    @RequestMapping("/surf")
    public ResponseResult add(HttpServletRequest request){
        int surfingNum = (int)redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
        String result = "当前共有"+surfingNum+"人正在看";
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(result);
    }

    @RequestMapping("/test")
    public ResponseResult test(HttpServletRequest request){
        int surfingNum = (int)redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
        String result = "当前共有"+surfingNum+"人正在看";
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(result);
    }

    @RequestMapping("/sum")
    public ResponseResult sum(HttpServletRequest request){
        long sum = vistorService.vists();
        String result = "浏览量共"+sum+"人次";
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(result);
    }

    @RequestMapping("/test/mq")
    public ResponseResult testMQ(HttpServletRequest request){
        rabbitProducer.stringSend();
        return ResponseResult.ResultHelper.successInstance().setMsg("ok");
    }
}
