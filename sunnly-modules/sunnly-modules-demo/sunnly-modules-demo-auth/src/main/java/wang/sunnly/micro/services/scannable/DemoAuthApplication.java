package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * AdminProducerApplication
 *
 * @author Sunnly
 * @since 2019/6/20 16:53
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients
@MapperScan("wang.sunnly.micro.services.scannable.admin.consumer.mapper")
public class DemoAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoAuthApplication.class, args);
    }
}
