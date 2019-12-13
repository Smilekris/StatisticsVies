package com.smile.monkeyserver.rest;

import com.smile.monkeyapi.enitity.ResponseResult;
import com.smile.monkeyserver.enity.Menu;
import com.smile.monkeyserver.enity.MonkeyAccountEnity;
import com.smile.monkeyserver.exception.MonkeyException;
import com.smile.monkeyserver.service.LoginService;
import com.smile.monkeyserver.util.MonkeySSOGenerator;
import com.smile.monkeyserver.util.PasswordEncryptor;
import com.smile.monkeyserver.util.RedisAssembleUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LoginController
 * @Author kris
 * @Date 2019/12/12
 **/
@RestController
@RequestMapping("/api")
@Api(tags = {"登录接口"},value = "登录接口")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody MonkeyAccountEnity monkeyAccountEnity){
        if(null == monkeyAccountEnity){
            throw new MonkeyException("账号/密码不能为空");
        }
        if(StringUtils.isEmpty(monkeyAccountEnity.getAccount())){
            throw new MonkeyException("账号不能为空");
        }
        if(StringUtils.isEmpty(monkeyAccountEnity.getPassword())){
            throw new MonkeyException("密码不能为空");
        }
        //存redis，若曾经登录过，直接根据redis上token解析
        String redisSSO = (String)redisTemplate.opsForValue().get(RedisAssembleUtil.assembleRedisAccount(monkeyAccountEnity.getAccount()));
        if(!StringUtils.isEmpty(redisSSO)){
            MonkeyAccountEnity redisAccount = MonkeySSOGenerator.decodeSSO(MonkeyAccountEnity.class,redisSSO);
            PasswordEncryptor encoderMd5 = new PasswordEncryptor(redisAccount.getSalt(), "sha-256");
            if (redisAccount.getPassword().equals(encoderMd5.encode(monkeyAccountEnity.getPassword()))){
                Map<String,String> result = new HashMap<>();
                result.put("monkeysso",redisSSO);
                return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(result);
            }else{
                throw new MonkeyException("账号/密码错误");
            }
        }

        MonkeyAccountEnity dataAccount = loginService.login(monkeyAccountEnity);
        if(null != dataAccount){
            String monkeySSO = MonkeySSOGenerator.generateSSO(dataAccount);
            Map<String,String> result = new HashMap<>();
            result.put("monkeysso",monkeySSO);
            redisTemplate.opsForValue().set(RedisAssembleUtil.assembleRedisAccount(monkeyAccountEnity.getAccount()),monkeySSO,MonkeySSOGenerator.LASTTIME, TimeUnit.HOURS);
            return ResponseResult.ResultHelper.successInstance().setMsg("ok").setResult(result);
        }
        return ResponseResult.ResultHelper.failInstance().setMsg("登录失败");
    }

    @PostMapping("/register")
    //TODO 账号不能相同
    public ResponseResult register(@RequestBody MonkeyAccountEnity monkeyAccountEnity){
        if(null == monkeyAccountEnity){
            throw new MonkeyException("账号参数为空");
        }
        if (StringUtils.isEmpty(monkeyAccountEnity.getAccount())){
            throw new MonkeyException("账号名不能为空");
        }
        if (StringUtils.isEmpty(monkeyAccountEnity.getPassword())){
            throw new MonkeyException("密码不能为空");
        }

        Integer register = loginService.register(monkeyAccountEnity);
        if (register>0){
            return ResponseResult.ResultHelper.successInstance().setMsg("您已注册成功，请在登录界面登录");
        }else {
            return ResponseResult.ResultHelper.failInstance().setMsg("注册失败，请检查参数是否正确");
        }
    }
}
