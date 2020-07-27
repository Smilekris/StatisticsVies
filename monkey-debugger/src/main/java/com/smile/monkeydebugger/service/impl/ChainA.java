package com.smile.monkeydebugger.service.impl;

import com.smile.monkeydebugger.service.ChainAsbtract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName ChainA
 * @Author yamei
 * @Date 2020/7/27
 **/
@Slf4j
@Service
public class ChainA extends ChainAsbtract {
    public final int ORDER = 1;
    public ChainA() {
        this.order = ORDER;
    }

    @Override
    protected void doPrint(String s) {
        String txt = "    A CLASS PRINT()";
        s = s + txt;
        log.info("chain===>{}",s);
        log.info(txt);
    }
}
