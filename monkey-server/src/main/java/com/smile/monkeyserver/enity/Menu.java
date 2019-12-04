package com.smile.monkeyserver.enity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName Menu
 * @Author kris
 * @Date 2019/11/29
 **/

@Data
public class Menu {

    public Integer id;

    public String menuName;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date createTime;

    public String createUser = "systemUser";

    public Integer userId = 0;
}
