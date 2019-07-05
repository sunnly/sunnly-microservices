package wang.sunnly.micro.services.scannable.security.auth.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SecurityOAuthClientProperties
 *
 * @author Sunnly
 * @create 2019/6/20 13:42
 */
@Component
@ConfigurationProperties(prefix = "sunnly.security.auth.user")
@Data
public class SecurityAuthUserProperties {

    private String tokenHeader;

    private byte[] pubKeyByte;

}
