package wang.sunnly.micro.services.scannable.security.auth.response.client.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wang.sunnly.micro.services.scannable.security.auth.core.interceptor.ClientAuthInterceptorAdapter;
import wang.sunnly.micro.services.scannable.security.auth.response.client.interceptor.ClientAuthInterceptor;
import wang.sunnly.micro.services.scannable.security.auth.response.core.properties.AuthCheckClientPathFilterProperties;

/**
 * @author Sunnly
 * @ClassName WebConfiguration
 * @Date 2019/6/16 0016 21:26
 **/
@Configuration
public class AuthReqClientWebConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthCheckClientPathFilterProperties authReqClientPathFilterProperties;
//
//    @Autowired
//    @Lazy
//    @Order(6)
//    private ClientAuthInterceptorAdapter clientAuthInterceptorAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientAuthInterceptorAdapter())
            .addPathPatterns(authReqClientPathFilterProperties.getIntercept())
            .excludePathPatterns(authReqClientPathFilterProperties.getExclude());

    }

    @Bean
//    @ConditionalOnMissingBean(ClientAuthInterceptorAdapter.class)
//    @Order(5)
    public ClientAuthInterceptorAdapter clientAuthInterceptorAdapter(){
        return new ClientAuthInterceptor();
    }

}
