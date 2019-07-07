package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import wang.sunnly.micro.services.scannable.tools.cache.annotation.EnableSunnlyCaching;
import wang.sunnly.micro.services.scannable.tools.cache.enums.SunnlyAdviceMode;

/**
 * DemoCacheApplication
 *
 * @author Sunnly
 * @create 2019/7/7 0007 19:44
 */
@SpringBootApplication
//@EnableSunnlyCaching(SunnlyAdviceMode.REDIS)
@EnableCaching()
public class DemoCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoCacheApplication.class, args);
    }
}
