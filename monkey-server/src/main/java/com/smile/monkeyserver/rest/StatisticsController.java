package com.smile.monkeyserver.rest;

import com.smile.monkeyapi.constants.RedisConstants;
import com.smile.monkeyapi.enitity.ResponseResult;
import com.smile.monkeyserver.amqp.RabbitProducer;
import com.smile.monkeyserver.enity.Menu;
import com.smile.monkeyserver.exception.MonkeyException;
import com.smile.monkeyserver.service.MenuService;
import com.smile.monkeyserver.service.MockAccluateService;
import com.smile.monkeyserver.service.VistorService;
import com.smile.monkeyserver.util.DistributedRedisLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author kris
 */
@RestController
@RequestMapping("/statistics")
@Api(tags = {"web统计信息"},value = "web统计信息")
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
    @Autowired
    private MenuService menuService;

    @GetMapping("/surf")
    @ApiOperation(value = "统计正在观看人数", notes="统计正在观看人数")
    public ResponseResult add(HttpServletRequest request) {
        int surfingNum = (int) redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
        String result = "当前共有" + surfingNum + "人正在看";
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(result);
    }

    @GetMapping("/test")
    public ResponseResult test(HttpServletRequest request) {
        int surfingNum = (int) redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
        String result = "当前共有" + surfingNum + "人正在看";
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(result);
    }

    @GetMapping("/sum")
    @ApiOperation(value = "统计浏览次数", notes="统计浏览次数")
    public ResponseResult sum(HttpServletRequest request) {
        long sum = vistorService.vists();
        String result = "共" + sum + "人次浏览";
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(result);
    }

    @GetMapping("/test/mq")
    public ResponseResult testMQ(HttpServletRequest request) {
        rabbitProducer.stringSend();
        return ResponseResult.ResultHelper.successInstance().setMsg("ok");
    }

    @GetMapping("/test/time")
    public ResponseResult testTime(HttpServletRequest request) {
        vistorService.test();
        return ResponseResult.ResultHelper.successInstance().setMsg("ok");
    }

    @GetMapping("/test/dis-lock")
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

    @GetMapping("/test/hello")
    public String testHello(HttpServletRequest request) {
        return "for test";
    }

    @GetMapping("/menu")
    public ResponseResult menu(){
        List<Menu> menuList = menuService.getMenuList();

        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(menuList);
    }

    @PostMapping("/menu")
    public ResponseResult addMenu(@RequestBody Menu menu){
        if(null == menu){
            throw new MonkeyException(ResponseResult.getFAIL(),"菜单为空");
        }
        if(null == menu.menuName){
            throw new MonkeyException(ResponseResult.getFAIL(),"菜单名为空");
        }

        menu.setCreateTime(new Date());
        Integer addMenuNum = menuService.addMenu(menu);
        if(addMenuNum > 0){
            return ResponseResult.ResultHelper.successInstance().setMsg("ok");
        }else {
            throw new MonkeyException(ResponseResult.getFAIL(),"添加菜单失败");
        }
    }

    @GetMapping("/random-menus")
    public ResponseResult randomMenu(){
        List<Menu> menuList = menuService.randomMenus();
        return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(menuList);
    }
}
