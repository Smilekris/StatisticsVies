package com.smile.monkeydebugger.service.impl;

import com.smile.monkeydebugger.service.ChainAsbtract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName ChainB
 * @Author yamei
 * @Date 2020/7/27
 **/
@Slf4j
@Service
public class ChainB extends ChainAsbtract {
    public static final int ORDER = 2;

    public ChainB() {
        this.order = ORDER;
    }

    @Override
    protected void doPrint(String s) {
        String txt = "    B CLASS PRINT()";
        s = s + txt;
        log.info("chain===>{}",s);
        log.info(txt);
    }
}
