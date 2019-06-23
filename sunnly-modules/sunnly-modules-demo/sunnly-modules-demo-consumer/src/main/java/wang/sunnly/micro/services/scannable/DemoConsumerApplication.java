package wang.sunnly.micro.services.scannable;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * DemoConsumerApplication
 *
 * @author Sunnly
 * @create 2019/6/24 0024 0:02
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients(basePackages = {"wang.sunnly.micro.services.scannable"})
@MapperScan("wang.sunnly.micro.services.scannable.demo.consumer.mapper")
public class DemoConsumerApplication {
}