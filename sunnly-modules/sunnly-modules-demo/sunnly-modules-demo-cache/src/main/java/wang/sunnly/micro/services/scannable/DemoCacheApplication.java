package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wang.sunnly.micro.services.scannable.tools.cache.annotation.EnableSunnlyCaching;
import wang.sunnly.micro.services.scannable.tools.cache.enums.SunnlyCacheMode;

import java.util.Date;

/**
 * DemoCacheApplication
 *
 * @author Sunnly
 * @since 2019/7/7 0007 19:44
 */
@SpringBootApplication
@EnableSunnlyCaching(SunnlyCacheMode.COLLECTION)
public class DemoCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoCacheApplication.class, args);
    }
}
