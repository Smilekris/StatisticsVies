package com.smile.monkeyserver.amqp;

import com.alibaba.fastjson.JSONObject;
import com.smile.monkeyapi.enitity.InterviewDTO;
import com.smile.monkeyserver.config.RabbitmqConfig;
import com.smile.monkeyserver.service.VistorService;
import com.smile.monkeyserver.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName RabbitDirectConsumer
 * @Author kris
 * @Date 2019/7/12
 **/
@Component
public class RabbitDirectConsumer {
    public final static Logger LOG = LoggerFactory.getLogger(RabbitDirectConsumer.class);
    @Autowired
    private AmqpTemplate rabbitmqTemplate;
    @Autowired
    private VistorService vistorService;

    @RabbitListener(queues = RabbitmqConfig.TEST_DIRECT_QUEUE_A,containerFactory = "rabbitListenerContainerFactory")
    public void recievedA(JSONObject message) {
        LOG.info("["+RabbitmqConfig.TEST_DIRECT_QUEUE_A+"] recieved message:" + message+"deal time"+ DateUtil.getCurrentTimeStr());
    }

    @RabbitListener(queues = RabbitmqConfig.TEST_DIRECT_QUEUE_B,containerFactory = "rabbitListenerContainerFactory")
    public void recievedB(JSONObject message) {
        LOG.info("["+RabbitmqConfig.TEST_DIRECT_QUEUE_B+"] recieved message:" + message+"deal time"+ DateUtil.getCurrentTimeStr());
    }

    @RabbitListener(queues = RabbitmqConfig.TEST_DIRECT_QUEUE_C,containerFactory = "rabbitListenerContainerFactory")
    public void recievedC(JSONObject message) {
        LOG.info("["+RabbitmqConfig.TEST_DIRECT_QUEUE_C+"] recieved message:" + message+"deal time"+ DateUtil.getCurrentTimeStr());
    }
}
