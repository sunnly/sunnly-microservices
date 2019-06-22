package wang.sunnly.micro.services.scannable.security.auth.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AuthReqClientPathFilterProperties
 *
 * @author Sunnly
 * @create 2019/6/21 11:17
 */
@Component
@ConfigurationProperties(prefix="sunnly.path-patterns.user")
public class AuthReqUserPathFilterProperties extends AuthCheckPathFilterProperties {


}
