package com.smile.monkeyserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author kris
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.smile.monkeyserver.mapper")
@EnableSwagger2
public class MonkeyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonkeyServerApplication.class, args);
    }

}
