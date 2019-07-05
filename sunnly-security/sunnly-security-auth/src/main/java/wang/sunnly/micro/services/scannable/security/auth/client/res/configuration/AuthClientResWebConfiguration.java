package wang.sunnly.micro.services.scannable.security.auth.client.res.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wang.sunnly.micro.services.scannable.security.auth.client.res.interceptor.ClientAuthInterceptor;
import wang.sunnly.micro.services.scannable.security.auth.core.interceptor.ClientAuthInterceptorAdapter;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.AuthCheckClientPathFilterProperties;

/**
 * @author Sunnly
 * @ClassName WebConfiguration
 * @Date 2019/6/16 0016 21:26
 **/
@Configurable
public class AuthClientResWebConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthCheckClientPathFilterProperties authCheckClientPathFilterProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientAuthInterceptorAdapter())
            .addPathPatterns(authCheckClientPathFilterProperties.getIntercept())
            .excludePathPatterns(authCheckClientPathFilterProperties.getExclude());

    }

    @Bean
    public ClientAuthInterceptorAdapter clientAuthInterceptorAdapter(){
        return new ClientAuthInterceptor();
    }

}
