package wang.sunnly.micro.services.scannable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * AdminConsumerApplication
 *
 * @author Sunnly
 * @create 2019/6/20 16:53
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AdminConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminConsumerApplication.class, args);
    }
}
