package wang.sunnly.micro.services.scannable.security.auth.response.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import wang.sunnly.micro.services.scannable.security.auth.response.core.properties.AuthCheckPathFilterProperties;

/**
 * AuthReqClientPathFilterProperties
 *
 * @author Sunnly
 * @create 2019/6/21 11:17
 */
@Component
@ConfigurationProperties(prefix="sunnly.path-patterns.client")
public class AuthCheckClientPathFilterProperties extends AuthCheckPathFilterProperties {


}
