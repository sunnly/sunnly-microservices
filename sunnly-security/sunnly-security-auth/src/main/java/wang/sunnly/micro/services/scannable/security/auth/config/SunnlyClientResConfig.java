package wang.sunnly.micro.services.scannable.security.auth.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import wang.sunnly.micro.services.scannable.security.auth.client.core.store.ClientTokenStore;
import wang.sunnly.micro.services.scannable.security.auth.client.res.api.RefreshClientPubKey;
import wang.sunnly.micro.services.scannable.security.auth.client.res.interceptor.ClientAuthInterceptor;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.AuthCheckClientPathFilterProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.tools.ClientInfoFromTokenHelper;

/**
 * SunnlyClientResConfig
 *
 * @author Sunnly
 * @since 2019/7/6 15:00
 */
public class SunnlyClientResConfig {

    @Bean
    @Order(4)
    @ConditionalOnMissingBean(ClientTokenStore.class)
    public ClientTokenStore clientTokenStore(){
        return new ClientTokenStore();
    }

    @Bean
    @Order(4)
    @ConditionalOnMissingBean(SecurityAuthClientProperties.class)
    public SecurityAuthClientProperties securityAuthClientProperties(){
        return new SecurityAuthClientProperties();
    }

    @Bean
    public AuthCheckClientPathFilterProperties authCheckClientPathFilterProperties(){
        return new AuthCheckClientPathFilterProperties();
    }

    @Bean
    public ClientInfoFromTokenHelper clientInfoFromTokenHelper(){
        return new ClientInfoFromTokenHelper();
    }

    @Bean
    @Order(10)
    public ClientAuthInterceptor clientAuthInterceptor(){
        return new ClientAuthInterceptor();
    }

    @Bean
    public RefreshClientPubKey refreshClientPubKey(){
        return new RefreshClientPubKey();
    }
}
