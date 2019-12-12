package com.smile.monkeyserver.enity;

import lombok.Data;

/**
 * @ClassName MonkeyAccountEnity
 * @Author kris
 * @Date 2019/12/11
 **/
@Data
public class MonkeyAccountEnity {
    Integer id;

    String account;

    String password;

    String description;

    String remark;

    /**
     *  0-man,1-female
     * */
    Integer sex;

    String location;

    Integer age;

    String nickName;
}
