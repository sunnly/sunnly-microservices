package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import wang.sunnly.micro.services.scannable.tools.redis.annotation.EnableSunnlyRedis;

/**
 * DemoRedisApplication
 *
 * @author Sunnly
 * @since 2019/7/9 13:43
 */
@SpringBootApplication
@EnableSunnlyRedis(1)
public class DemoRedisApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoRedisApplication.class, args);
    }
}
