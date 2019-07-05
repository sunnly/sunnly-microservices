package wang.sunnly.micro.services.scannable.security.auth.user.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.AuthCheckUserPathFilterProperties;
import wang.sunnly.micro.services.scannable.security.auth.user.core.interceptor.UserAuthInterceptor;

/**
 * @author Sunnly
 * @ClassName WebConfiguration
 * @Date 2019/6/16 0016 21:26
 **/
@Configurable
public class AuthReqUserWebConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthCheckUserPathFilterProperties authCheckUserPathFilterProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAuthInterceptor())
            .addPathPatterns(authCheckUserPathFilterProperties.getIntercept())
            .excludePathPatterns(authCheckUserPathFilterProperties.getExclude());

    }

    @Bean
    UserAuthInterceptor userAuthInterceptor(){
        return new UserAuthInterceptor();
    }

}
