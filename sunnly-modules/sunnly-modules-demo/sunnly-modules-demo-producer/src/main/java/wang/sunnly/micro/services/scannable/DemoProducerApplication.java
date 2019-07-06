package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
import wang.sunnly.micro.services.scannable.security.auth.annotation.EnableSunnlyClient;

/**
 * DemoProducerApplication
 *
 * @author Sunnly
 * @create 2019/6/21 14:40
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients
//@EnableScheduling
//@SunnlyClientEnable
@EnableSunnlyClient
@MapperScan("wang.sunnly.micro.services.scannable.demo.producer.mapper")
public class DemoProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProducerApplication.class, args);
    }
}
