package wang.sunnly.micro.services.scannable.security.auth.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import wang.sunnly.micro.services.scannable.security.auth.client.core.store.ClientTokenStore;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.AuthCheckClientPathFilterProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.AuthCheckUserPathFilterProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthUserProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.tools.ClientInfoFromTokenHelper;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.tools.JWTInfoFromTokenHelper;
import wang.sunnly.micro.services.scannable.security.auth.user.core.api.RefreshUserPubKey;
import wang.sunnly.micro.services.scannable.security.auth.user.core.interceptor.UserAuthInterceptor;
import wang.sunnly.micro.services.scannable.security.auth.user.reqs.interceptor.FeignOkHttpUserInterceptor;

/**
 * SunnlyUserConfig
 *
 * @author Sunnly
 * @create 2019/7/6 15:00
 */
public class SunnlyUserConfig {

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
    public AuthCheckUserPathFilterProperties authCheckUserPathFilterProperties(){
        return new AuthCheckUserPathFilterProperties();
    }

    @Bean
    public RefreshUserPubKey refreshUserPubKey(){
        return new RefreshUserPubKey();
    }

    @Bean
    public SecurityAuthUserProperties securityAuthUserProperties(){
        return new SecurityAuthUserProperties();
    }

    @Bean
    public JWTInfoFromTokenHelper jwtInfoFromTokenHelper(){
        return new JWTInfoFromTokenHelper();
    }

    @Bean
    UserAuthInterceptor userAuthInterceptor(){
        return new UserAuthInterceptor();
    }

    @Bean
    FeignOkHttpUserInterceptor feignOkHttpUserInterceptor(){
        return new FeignOkHttpUserInterceptor();
    }
}
