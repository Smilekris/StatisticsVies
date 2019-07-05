package app.rest;

import app.constants.RedisConstants;
import app.enitity.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private RedisTemplate redisTemplate;

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
}
