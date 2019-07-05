package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author yamei
 */
@SpringBootApplication
@ServletComponentScan
public class MonkeyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonkeyServerApplication.class, args);
    }

}
