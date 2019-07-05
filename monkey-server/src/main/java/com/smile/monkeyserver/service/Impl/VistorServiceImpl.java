package com.smile.monkeyserver.service.Impl;

import com.smile.monkeyapi.enitity.InterviewDTO;
import com.smile.monkeyserver.mapper.RecordMapper;
import com.smile.monkeyserver.service.VistorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ClassName VistorServiceImpl
 * @Author yamei
 * @Date 2019/7/5
 **/

@Service
public class VistorServiceImpl implements VistorService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public long vists() {
        return recordMapper.visits();
    }

    @Override
    public int insert(InterviewDTO interviewDTO) {
        return recordMapper.addRecord(interviewDTO);
    }
}
