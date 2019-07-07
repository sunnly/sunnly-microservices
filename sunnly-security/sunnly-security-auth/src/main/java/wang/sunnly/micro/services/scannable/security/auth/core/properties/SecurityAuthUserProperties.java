package wang.sunnly.micro.services.scannable.security.auth.core.properties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * SecurityOAuthClientProperties
 *
 * @author Sunnly
 * @create 2019/6/20 13:42
 */
@EnableConfigurationProperties(SecurityAuthUserProperties.class)
@ConfigurationProperties(prefix = "sunnly.security.auth.user")
@Data
public class SecurityAuthUserProperties {

    private String tokenHeader;

    private byte[] pubKeyByte;

}
