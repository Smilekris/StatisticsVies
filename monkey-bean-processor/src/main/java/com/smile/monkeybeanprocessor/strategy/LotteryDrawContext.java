package com.smile.monkeybeanprocessor.strategy;

import cn.hutool.core.lang.Assert;
import com.smile.monkeybeanprocessor.constant.LotteryDrawTypeEnum;
import com.smile.monkeybeanprocessor.service.LotteryDraw;
import com.smile.monkeybeanprocessor.util.SpringUtil;

import java.util.Map;

/**
 * @ClassName LotteryDrawContext
 * @Author kris
 * @Date 2020/8/17
 **/
public class LotteryDrawContext {
    private final Map<String, Class<? extends LotteryDraw>> context ;


    protected LotteryDrawContext(Map<String, Class<? extends LotteryDraw>> context){
        this.context = context;
    }

    public LotteryDraw getInstance(LotteryDrawTypeEnum lotteryDrawTypeEnum){
        Assert.isTrue(lotteryDrawTypeEnum!=null,"类型不能为空");
        if(context == null || context.get(lotteryDrawTypeEnum.getTypeName()) == null){
            throw new IllegalArgumentException("扩展信息处理器获取信息失败");
        }
        Class<? extends LotteryDraw> clazz = context.get(lotteryDrawTypeEnum.getTypeName());
        return SpringUtil.getBean(clazz);
    }
}
