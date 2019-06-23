package wang.sunnly.micro.services.scannable.auth.producer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import wang.sunnly.micro.services.scannable.auth.producer.interceptor.ClientResponseTokenInterceptor;
import wang.sunnly.micro.services.scannable.security.auth.core.interceptor.ClientAuthInterceptorAdapter;

/**
 * FeignResponseClientConfig
 *
 * @author Sunnly
 * @create 2019/6/22 0022 15:32
 */
@Configuration
public class FeignResponseClientConfig {

    @Bean
    @Order(3)
    public ClientAuthInterceptorAdapter clientAuthInterceptorAdapter(){
        return new ClientResponseTokenInterceptor();
    }
}