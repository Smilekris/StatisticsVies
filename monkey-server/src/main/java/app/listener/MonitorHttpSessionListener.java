package app.listener;

import app.constants.RedisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class MonitorHttpSessionListener implements HttpSessionListener {


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("http seesion listener->"+session);
        System.out.println("session created");
        Boolean ac_b = redisTemplate.hasKey(RedisConstants.ACTIVE_NUM);
        if(ac_b){
            int activeNum = (int)redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
            activeNum = increase(activeNum);
            redisTemplate.opsForValue().set(RedisConstants.ACTIVE_NUM,activeNum);
        }else{
            redisTemplate.opsForValue().set(RedisConstants.ACTIVE_NUM,1);
        }
        System.out.println("session created active"+(int)redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println(session);
        System.out.println("session destroyed");
        Boolean ac_b = redisTemplate.hasKey(RedisConstants.ACTIVE_NUM);
        if(ac_b){
            int activeNum = (int)redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
            activeNum = decrease(activeNum);
            redisTemplate.opsForValue().set(RedisConstants.ACTIVE_NUM,activeNum);
        }else{
            redisTemplate.opsForValue().set(RedisConstants.ACTIVE_NUM,0);
        }
        System.out.println("session created destory"+(int)redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM));
    }

    public int increase(int activeNum){
        return ++activeNum;
    }

    public int decrease(int activeNum){
        return --activeNum;
    }
}
