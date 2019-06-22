package wang.sunnly.micro.services.scannable.auth.producer.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import wang.sunnly.micro.services.scannable.auth.producer.interceptor.ClientRequestTokenInterceptor;

/**
 *
 * FeignRequestClientConfiguration
 * @author Sunnly
 * @create 2019/6/21 10:07
 */
@Configurable
public class FeignRequestClientConfiguration {

    @Bean
    public ClientRequestTokenInterceptor clientTokenInterceptor(){
        return new ClientRequestTokenInterceptor();
    }

}
