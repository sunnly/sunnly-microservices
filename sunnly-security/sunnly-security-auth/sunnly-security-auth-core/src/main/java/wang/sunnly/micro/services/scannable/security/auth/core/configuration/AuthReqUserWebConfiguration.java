package wang.sunnly.micro.services.scannable.security.auth.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wang.sunnly.micro.services.scannable.security.auth.core.interceptor.UserAuthUserInterceptor;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.AuthReqUserPathFilterProperties;

/**
 * @author Sunnly
 * @ClassName WebConfiguration
 * @Date 2019/6/16 0016 21:26
 **/
@Configuration
public class AuthReqUserWebConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthReqUserPathFilterProperties authReqUserPathFilterProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAuthRestInterceptor())
            .addPathPatterns(authReqUserPathFilterProperties.getIntercept())
            .excludePathPatterns(authReqUserPathFilterProperties.getExclude());

    }

    @Bean
    UserAuthUserInterceptor userAuthRestInterceptor(){
        return new UserAuthUserInterceptor();
    }

}
