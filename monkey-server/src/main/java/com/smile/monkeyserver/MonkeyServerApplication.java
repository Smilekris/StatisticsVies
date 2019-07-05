package com.smile.monkeyserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author kris
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.smile.monkeyserver.mapper")
public class MonkeyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonkeyServerApplication.class, args);
    }

}
