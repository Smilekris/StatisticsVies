package com.smile.monkeybeanprocessor.service.impl;

import com.smile.monkeybeanprocessor.annotation.LotterDrawAnnotation;
import com.smile.monkeybeanprocessor.service.LotteryDraw;
import org.springframework.stereotype.Service;

/**
 * @ClassName DinnerLotteryDraw
 * @Author kris
 * @Date 2020/8/17
 **/
@Service
@LotterDrawAnnotation(type = "dinner")
public class DinnerLotteryDraw implements LotteryDraw {
    @Override
    public String draw() {
        return "dinner";
    }
}
