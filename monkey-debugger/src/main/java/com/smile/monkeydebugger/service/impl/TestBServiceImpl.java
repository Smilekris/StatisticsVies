package com.smile.monkeydebugger.service.impl;

import com.smile.monkeydebugger.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName TestBServiceImpl
 * @Author kris
 * @Date 2020/7/27
 **/
@Service
@Slf4j
public class TestBServiceImpl implements TestService {

    @Override
    public void doPrint() {
        log.info("B Class");
    }
}
