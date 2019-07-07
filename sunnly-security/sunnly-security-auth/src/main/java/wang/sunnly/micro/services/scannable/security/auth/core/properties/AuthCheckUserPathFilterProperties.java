package wang.sunnly.micro.services.scannable.security.auth.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * AuthReqClientPathFilterProperties
 *
 * @author Sunnly
 * @create 2019/6/21 11:17
 */
@EnableConfigurationProperties(AuthCheckUserPathFilterProperties.class)
@ConfigurationProperties(prefix="sunnly.path-patterns.user")
public class AuthCheckUserPathFilterProperties extends AuthCheckPathFilterProperties {


}
