package wang.sunnly.micro.services.scannable.security.auth.response.client.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import wang.sunnly.micro.services.scannable.security.auth.response.client.interceptor.ClientAuthInterceptor;
import wang.sunnly.micro.services.scannable.security.auth.response.client.properties.AuthReqClientPathFilterProperties;

/**
 * @author Sunnly
 * @ClassName WebConfiguration
 * @Date 2019/6/16 0016 21:26
 **/
@Configuration
public class AuthReqClientWebConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private AuthReqClientPathFilterProperties authReqClientPathFilterProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientAuthInterceptor())
            .addPathPatterns(authReqClientPathFilterProperties.getIntercept())
            .excludePathPatterns(authReqClientPathFilterProperties.getExclude());

    }

    @Bean
    ClientAuthInterceptor clientAuthInterceptor(){
        return new ClientAuthInterceptor();
    }

}
