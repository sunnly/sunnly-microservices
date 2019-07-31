package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wang.sunnly.micro.services.scannable.tools.logger.annotation.EnableSunnlyLogger;

/**
 * DemoLoggerApplication
 *
 * @author Sunnly
 * @since 2019/7/24 11:58
 */
@SpringBootApplication
@EnableSunnlyLogger
public class DemoLoggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoLoggerApplication.class, args);
    }
}
