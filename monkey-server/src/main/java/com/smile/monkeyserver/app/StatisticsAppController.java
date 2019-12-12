package com.smile.monkeyserver.app;

import com.smile.monkeyapi.constants.RedisConstants;
import com.smile.monkeyapi.enitity.ResponseResult;
import com.smile.monkeyserver.amqp.RabbitProducer;
import com.smile.monkeyserver.rest.StatisticsController;
import com.smile.monkeyserver.service.MenuService;
import com.smile.monkeyserver.service.MockAccluateService;
import com.smile.monkeyserver.service.VistorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName StatisticsAppController
 * @Author yamei
 * @Date 2019/12/10
 **/
@RestController
@RequestMapping("/statistics/app")
@Api(tags = {"app统计信息"},value = "app统计信息")
public class StatisticsAppController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsAppController.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private VistorService vistorService;
    @Autowired
    private RabbitProducer rabbitProducer;
    @Autowired
    private MockAccluateService mockAccluateService;
    @Autowired
    private MenuService menuService;


    @GetMapping("/surf")
    @ApiOperation(value = "app-统计正在观看人数", notes="app-统计正在观看人数")
    public ResponseResult add(HttpServletRequest request) {
        int surfingNum = (int) redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(surfingNum);
    }

    @GetMapping("/sum")
    @ApiOperation(value = "app-统计浏览次数", notes="app-统计浏览次数")
    public ResponseResult sum(HttpServletRequest request) {
        long sum = vistorService.vists();
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(sum);
    }
}
