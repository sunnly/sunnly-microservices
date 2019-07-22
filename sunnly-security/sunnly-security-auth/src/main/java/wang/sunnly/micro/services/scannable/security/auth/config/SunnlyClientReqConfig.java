package wang.sunnly.micro.services.scannable.security.auth.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import wang.sunnly.micro.services.scannable.security.auth.client.core.store.ClientTokenStore;
import wang.sunnly.micro.services.scannable.security.auth.client.server.interceptor.FeignOkHttpClientInterceptor;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;

/**
 * SunnlyClientReqConfig
 *
 * @author Sunnly
 * @since 2019/7/6 0006 1:26
 */
public class SunnlyClientReqConfig {

    @Bean
    @Order(5)
    @ConditionalOnMissingBean(ClientTokenStore.class)
    public ClientTokenStore clientTokenStore(){
        return new ClientTokenStore();
    }

    @Bean
    @Order(5)
    @ConditionalOnMissingBean(SecurityAuthClientProperties.class)
    public SecurityAuthClientProperties securityAuthClientProperties(){
        return new SecurityAuthClientProperties();
    }

    @Bean
    @Order(10)
    public FeignOkHttpClientInterceptor feignOkHttpClientInterceptor(){
        return new FeignOkHttpClientInterceptor();
    }
}
