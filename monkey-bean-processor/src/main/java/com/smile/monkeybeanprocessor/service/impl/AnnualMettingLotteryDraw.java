package com.smile.monkeybeanprocessor.service.impl;

import com.smile.monkeybeanprocessor.annotation.LotterDrawAnnotation;
import com.smile.monkeybeanprocessor.constant.LotteryDrawTypeEnum;
import com.smile.monkeybeanprocessor.service.LotteryDraw;
import org.springframework.stereotype.Service;

/**
 * @ClassName AnnualMettingLotteryDraw
 * @Author kris
 * @Date 2020/8/17
 **/
@Service
@LotterDrawAnnotation(type="annual-metting")
public class AnnualMettingLotteryDraw implements LotteryDraw {
    @Override
    public String draw() {
        return "meetting";
    }
}
