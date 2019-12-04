package com.smile.monkeyserver.mapper;

import com.smile.monkeyserver.enity.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuMapper {

    @Insert("insert into t_menu (menu_name,create_time,create_user,user_id) values (#{menuName},#{createTime},#{createUser},#{userId})")
    public Integer addMenu(Menu menu);

    @Select("select id,menu_name as menuName,create_time as createTime,create_user,user_id from t_menu ")
    public List<Menu> menus();
}
