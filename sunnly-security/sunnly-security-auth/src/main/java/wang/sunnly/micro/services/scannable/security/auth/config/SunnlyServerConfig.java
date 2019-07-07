package wang.sunnly.micro.services.scannable.security.auth.config;

import org.springframework.context.annotation.Bean;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.AuthCheckClientPathFilterProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;

/**
 * SunnlyServerConfig
 *
 * @author Sunnly
 * @create 2019/7/5 0005 23:02
 */
public class SunnlyServerConfig {

    @Bean
    public AuthCheckClientPathFilterProperties authCheckClientPathFilterProperties(){
        return new AuthCheckClientPathFilterProperties();
    }

    @Bean
    public SecurityAuthClientProperties securityAuthClientProperties(){
        return new SecurityAuthClientProperties();
    }

}
