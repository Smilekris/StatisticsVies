package com.smile.monkeydebugger.service.impl;

import com.smile.monkeydebugger.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName TestCServiceImpl
 * @Author kris
 * @Date 2020/7/27
 **/
@Slf4j
@Service
public class TestCServiceImpl implements TestService {
    @Override
    public void doPrint() {
        log.info("C Class");
    }
}
