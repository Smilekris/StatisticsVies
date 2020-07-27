package com.smile.monkeydebugger.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName User
 * @Author kris
 * @Date 2020/7/24
 **/
@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String sex;
}
