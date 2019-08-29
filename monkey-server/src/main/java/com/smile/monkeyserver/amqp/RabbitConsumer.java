package com.smile.monkeyserver.amqp;

import com.alibaba.fastjson.JSONObject;
import com.smile.monkeyapi.constants.RedisConstants;
import com.smile.monkeyapi.enitity.InterviewDTO;
import com.smile.monkeyserver.config.RabbitmqConfig;
import com.smile.monkeyserver.service.VistorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RabbitConsumer {
    public final static Logger LOG = LoggerFactory.getLogger(RabbitConsumer.class);
    @Autowired
    private AmqpTemplate rabbitmqTemplate;
    @Autowired
    private VistorService vistorService;
    @Autowired
    private RabbitProducer producer;
    @Autowired
    private RedisTemplate redisTemplate;

    private final static String DECREASE = "decrease";
    private final static String MSG_TYPE = "type";

    /**
     * 消息消费
     *
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitListener(queues = RabbitmqConfig.STATISTIS_QUEUE, containerFactory = "rabbitListenerContainerFactory")
    public void recieved(JSONObject message) {
        LOG.info("[RabbitConsumer] recieved message:" + message + "deal time" + dealWithTime());
        JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(message));
        if ("db".equals(json.get(MSG_TYPE))) {
            LOG.info("begin invoke mq to decrease activeNum");
            producer.sendDecreaseActiveNum();
            LOG.info("begin deal with db");
            InterviewDTO interviewDTO = new InterviewDTO();
            interviewDTO.setIp(json.getString("ip"));
            interviewDTO.setLoadTime((Long) json.get("date"));
            int insert = vistorService.insert(interviewDTO);
            LOG.info("insert num->" + insert);
        }
    }

    @RabbitListener(queues = RabbitmqConfig.ACTIVE_NUM_QUEUE, containerFactory = "rabbitListenerContainerFactory")
    public void decreaseActiveNum(JSONObject message) {
        LOG.info("[RabbitConsumer-decreaseActiveNum] recieved message:" + message + "deal time" + dealWithTime());
        JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(message));
        if (DECREASE.equals(json.get(MSG_TYPE))) {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOG.info("begin deal with " + DECREASE);
            int activeNum = (int) redisTemplate.opsForValue().get(RedisConstants.ACTIVE_NUM);
            activeNum = decrease(activeNum);
            redisTemplate.opsForValue().set(RedisConstants.ACTIVE_NUM, activeNum);
        }
    }

    private String dealWithTime() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return dateString;
    }

    public int decrease(int activeNum) {
        return --activeNum;
    }

    @RabbitListener(queues = RabbitmqConfig.TEST_BLOCK_QUEUE, containerFactory = "rabbitListenerContainerFactory")
    public void proceeTestCjw(JSONObject message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("[RabbitConsumer] recieved message:" + message + "deal time" + dealWithTime());
    }
}
