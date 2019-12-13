package com.smile.monkeyserver.mapper;

import com.smile.monkeyserver.enity.MonkeyAccountEnity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LoginMapper {

    @Insert("INSERT INTO t_account (account,nick_name,password,salt,age,sex,description,remark,location) values (#{account},#{nickName},#{password},#{salt},#{age},#{sex},#{description},#{remark},#{location})")
    @Options(useGeneratedKeys=true,keyProperty="id")
    public int register(MonkeyAccountEnity monkeyAccountEnity);


    @Select("SELECT * FROM t_account where account =#{account}")
    public MonkeyAccountEnity queryByAccount(String account);

    @Select("SELECT account from t_account ")
    public List<String> queryAllAccount();
}
