package com.smile.monkeyserver.service;

import com.smile.monkeyapi.enitity.InterviewDTO;

public interface VistorService {

    public long vists();

    public int insert(InterviewDTO interviewDTO);

    public void sendMQTask(String ip,Long date);
}
