package com.smile.monkeyserver.init;

import com.smile.monkeyapi.constants.RedisConstants;
import com.smile.monkeyserver.amqp.RabbitDirectProdicer;
import com.smile.monkeyserver.amqp.RabbitProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName InitialMethod
 * @Author yamei
 * @Date 2019/7/11
 **/
@Component
public class InitialMethod implements CommandLineRunner {
    public final static Logger LOG = LoggerFactory.getLogger(InitialMethod.class);

    @Autowired
    private RabbitProducer rabbitProducer;
    @Autowired
    private RabbitDirectProdicer directProdicer;

    private final static Integer FORTIME = 6;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        redisTemplate.opsForValue().set(RedisConstants.ACTIVE_NUM,0);
        ExecutorService executorService = new ThreadPoolExecutor(2,8,2, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(1000));
//        for(int i= 0;i<FORTIME;i++){
//            LOG.info("发送mq-》第"+i+"次");
////            executorService.execute(()->{rabbitProducer.sendTestCjw();});
//            if(i%3 ==0){
//                executorService.execute(()->directProdicer.sendTestA());
//            }else if(i%3 ==1){
//                executorService.execute(()->directProdicer.sendTestB());
//            }else if(i%3 ==2){
//                executorService.execute(()->directProdicer.sendTestC());
//            }
//        }
        executorService.shutdown();
    }
}
