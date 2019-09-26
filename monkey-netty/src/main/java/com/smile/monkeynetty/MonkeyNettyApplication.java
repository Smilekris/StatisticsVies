package com.smile.monkeynetty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @ClassName MonkeyNettyApplication
 * @Author kris
 * @Date 2019/9/26
 **/
@SpringBootApplication
@ServletComponentScan
public class MonkeyNettyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonkeyNettyApplication.class, args);
    }
}
