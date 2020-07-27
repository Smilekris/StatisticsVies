package com.smile.monkeydebugger.service.impl;

import com.smile.monkeydebugger.service.ChainAsbtract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName ChainC
 * @Author yamei
 * @Date 2020/7/27
 **/
@Slf4j
@Service
public class ChainC extends ChainAsbtract {
    public final int ORDER = 3;
    public ChainC() {
        this.order = ORDER;
    }

    @Override
    protected void doPrint(String s) {
        String txt = "    C CLASS PRINT()";
        s = s + txt;
        log.info("chain===>{}",s);
        log.info(txt);
    }
}
