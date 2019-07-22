package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MqConsumerApplication
 *
 * @author Sunnly
 * @since 2019/7/19 10:49
 */
@SpringBootApplication
public class MqConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqConsumerApplication.class, args);
    }
}
