package com.smile.monkeyserver.mapper;

import com.smile.monkeyapi.enitity.InterviewDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author kris
 */
@Component
public interface RecordMapper {

    @Select("SELECT COUNT(1) FROM t_interview")
    public long visits();

    @Insert("insert into t_interview (ip,load_time) value (#{ip},#{loadTime})")
    public int addRecord(InterviewDTO interviewDTO);

    @Insert("insert into t_test (loadtime) value (#{loadTime})")
    public int addtestRecord(@Param("loadTime") Date loadTime);
}
