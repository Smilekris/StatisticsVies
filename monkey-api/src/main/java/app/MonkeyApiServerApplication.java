package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author yamei
 */
@SpringBootApplication
@ServletComponentScan
public class MonkeyApiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonkeyApiServerApplication.class, args);
    }

}
