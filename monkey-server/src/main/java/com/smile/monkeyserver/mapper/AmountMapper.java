package com.smile.monkeyserver.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface AmountMapper {

    @Update("update t_user_amount set amount = amount-#{decreAmount},update_time = #{updateTime} where user_id = #{userId}")
    public int decreaseAmount(@Param("decreAmount") Integer decreAmount,@Param("updateTime") Date updateTime,@Param("userId") Integer userId);
}
