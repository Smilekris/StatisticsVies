package com.smile.monkeyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author kris
 */
@SpringBootApplication
@ServletComponentScan
public class MonkeyApiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonkeyApiServerApplication.class, args);
    }

}
