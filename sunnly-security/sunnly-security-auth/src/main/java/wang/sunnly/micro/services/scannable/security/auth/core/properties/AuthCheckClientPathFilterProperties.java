package wang.sunnly.micro.services.scannable.security.auth.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * AuthCheckClientPathFilterProperties
 * 拦截器配置，也可以用IgnoreClientToken放行
 * @author Sunnly
 * @since 2019/6/21 11:17
 */
@EnableConfigurationProperties(AuthCheckClientPathFilterProperties.class)
@ConfigurationProperties(prefix="sunnly.path-patterns.client")
public class AuthCheckClientPathFilterProperties extends AuthCheckPathFilterProperties {

}
