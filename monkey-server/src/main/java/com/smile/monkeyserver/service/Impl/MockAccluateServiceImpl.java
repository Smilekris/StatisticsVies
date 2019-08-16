package com.smile.monkeyserver.service.Impl;

import com.smile.monkeyserver.mapper.AmountMapper;
import com.smile.monkeyserver.service.MockAccluateService;
import com.smile.monkeyserver.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MockAccluateServiceImpl
 * @Author yamei
 * @Date 2019/8/14
 **/
@Service
public class MockAccluateServiceImpl implements MockAccluateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockAccluateServiceImpl.class);
    @Autowired
    private AmountMapper amountMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int decreaseAmount(Integer userId, Integer decrAmount) {
        LOGGER.info("enter decreaseAmount method time{}", DateUtil.getCurrentTimeStr());
        int updateNum = amountMapper.decreaseAmount(decrAmount, new Date(), userId);
        LOGGER.info("exit decreaseAmount method time{},result:{}", DateUtil.getCurrentTimeStr(),updateNum);
        return updateNum;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deductMoney(Integer userId, Integer amount) {
        decreaseAmount(userId,amount);
        redisTemplate.opsForValue().set("usertest","00",1, TimeUnit.MINUTES);
    }

}
