package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * AdminProducerApplication
 *
 * @author Sunnly
 * @create 2019/6/20 16:53
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@MapperScan("wang.sunnly.micro.services.scannable.admin.consumer.mapper")
public class AdminProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminProducerApplication.class, args);
    }
}
