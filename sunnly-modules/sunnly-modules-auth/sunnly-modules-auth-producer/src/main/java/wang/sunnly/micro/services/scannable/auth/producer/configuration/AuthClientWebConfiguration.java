package wang.sunnly.micro.services.scannable.auth.producer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.AuthCheckClientPathFilterProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.interceptor.ClientAuthInterceptorAdapter;

/**
 * @author Sunnly
 * @ClassName WebConfiguration
 * @Date 2019/6/16 0016 21:26
 **/
@Configuration
public class AuthClientWebConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private AuthCheckClientPathFilterProperties authReqClientPathFilterProperties;

    @Autowired
    @Lazy
    @Order(6)
    private ClientAuthInterceptorAdapter clientAuthInterceptorAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientAuthInterceptorAdapter)
            .addPathPatterns(authReqClientPathFilterProperties.getIntercept())
                .excludePathPatterns(authReqClientPathFilterProperties.getExclude());

    }
}
