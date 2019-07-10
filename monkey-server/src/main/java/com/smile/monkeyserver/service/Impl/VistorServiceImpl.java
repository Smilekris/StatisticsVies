package com.smile.monkeyserver.service.Impl;

import com.smile.monkeyapi.enitity.InterviewDTO;
import com.smile.monkeyserver.amqp.RabbitProducer;
import com.smile.monkeyserver.mapper.RecordMapper;
import com.smile.monkeyserver.service.VistorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName VistorServiceImpl
 * @Author yamei
 * @Date 2019/7/5
 **/

@Service
public class VistorServiceImpl implements VistorService {

    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private RabbitProducer rabbitProducer;

    @Override
    public long vists() {
        return recordMapper.visits();
    }

    @Override
    public int insert(InterviewDTO interviewDTO) {
        return recordMapper.addRecord(interviewDTO);
    }

    @Override
    public void sendMQTask(String ip, Long date) {
        rabbitProducer.sendMqTask(ip,date);
    }

    @Override
    public int test(){
        return recordMapper.addtestRecord(new Date());
    }
}
