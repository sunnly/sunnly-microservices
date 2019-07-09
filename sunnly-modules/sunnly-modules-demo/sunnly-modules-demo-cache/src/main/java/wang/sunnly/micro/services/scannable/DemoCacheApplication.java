package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wang.sunnly.micro.services.scannable.tools.cache.annotation.EnableSunnlyCaching;

/**
 * DemoCacheApplication
 *
 * @author Sunnly
 * @create 2019/7/7 0007 19:44
 */
@SpringBootApplication
@EnableSunnlyCaching
public class DemoCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoCacheApplication.class, args);
    }
}
