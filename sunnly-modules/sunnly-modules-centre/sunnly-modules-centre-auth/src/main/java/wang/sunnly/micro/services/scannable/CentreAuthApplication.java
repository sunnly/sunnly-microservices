package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
import wang.sunnly.micro.services.scannable.security.auth.annotation.EnableSunnlyServer;

/**
 * CentreAuthApplication
 *
 * @author Sunnly
 * @since 2019/6/21 0021 0:05
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients
@EnableSunnlyServer
@MapperScan("wang.sunnly.micro.services.scannable.centre.auth.mapper")
public class CentreAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(CentreAuthApplication.class, args);
    }
}
