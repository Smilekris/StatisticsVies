package com.smile.monkeybeanprocessor.rest;

import com.smile.monkeybeanprocessor.constant.LotteryDrawTypeEnum;
import com.smile.monkeybeanprocessor.strategy.LotteryDrawContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LotteryDrawController
 * @Author kris
 * @Date 2020/8/17
 **/
@RestController
@RequestMapping("/mall")
public class LotteryDrawController {

    private final LotteryDrawContext context;

    public LotteryDrawController(LotteryDrawContext context){
        this.context = context;
    }

    @GetMapping("/test")
    public String test (String type){
        return context.getInstance(LotteryDrawTypeEnum.getEnum(type)).draw();
    }

}
