package com.smile.monkeyserver.mapper;

import com.smile.monkeyapi.enitity.InterviewDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author kris
 */
@Component
public interface RecordMapper {

    @Select("SELECT COUNT(1) FROM t_interview")
    public long visits();

    @Insert("insert into t_interview (ip,load_time) value (#{ip},#{loadTime})")
    public int addRecord(InterviewDTO interviewDTO);
}
