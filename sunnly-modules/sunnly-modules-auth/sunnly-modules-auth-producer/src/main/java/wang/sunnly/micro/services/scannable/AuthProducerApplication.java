package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
import wang.sunnly.micro.services.scannable.security.auth.annotation.EnableSunnlyServer;

/**
 * AuthProducerApplication
 *
 * @author Sunnly
 * @create 2019/6/21 0021 0:05
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients
@EnableSunnlyServer
@MapperScan("wang.sunnly.micro.services.scannable.auth.producer.mapper")
public class AuthProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthProducerApplication.class, args);
    }
}
