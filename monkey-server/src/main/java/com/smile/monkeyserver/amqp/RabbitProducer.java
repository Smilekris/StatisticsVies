package com.smile.monkeyserver.amqp;

import com.alibaba.fastjson.JSONObject;
import com.smile.monkeyserver.config.RabbitmqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RabbitProducer {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void stringSend() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("[string] send msg:" + dateString);
        // 第一个参数为刚刚定义的队列名称
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.STATISTIS_QUEUE, dateString);
    }

    public void sendMqTask(String ip,Long date){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","db");
        jsonObject.put("ip",ip);
        jsonObject.put("date",date);
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.STATISTIS_QUEUE,jsonObject);
    }

    public void sendTestCjw(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test","ccc");
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.TEST_BLOCK_QUEUE,jsonObject);
    }

    public void sendDecreaseActiveNum(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","decrease");
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.ACTIVE_NUM_QUEUE,jsonObject);
    }
}
