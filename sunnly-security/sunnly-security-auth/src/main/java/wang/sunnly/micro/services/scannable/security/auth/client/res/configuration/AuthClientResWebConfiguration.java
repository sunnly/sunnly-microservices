package wang.sunnly.micro.services.scannable.security.auth.client.res.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wang.sunnly.micro.services.scannable.security.auth.core.interceptor.ClientAuthInterceptorAdapter;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.AuthCheckClientPathFilterProperties;

/**
 * AuthClientResWebConfiguration
 *
 * @author Sunnly
 * @create 2019/6/16 0016 21:26
 **/
@Configurable
@Order(10)
public class AuthClientResWebConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthCheckClientPathFilterProperties authCheckClientPathFilterProperties;

    @Autowired
    private ClientAuthInterceptorAdapter clientAuthInterceptorAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientAuthInterceptorAdapter)
            .addPathPatterns(authCheckClientPathFilterProperties.getIntercept())
            .excludePathPatterns(authCheckClientPathFilterProperties.getExclude());

    }


}
