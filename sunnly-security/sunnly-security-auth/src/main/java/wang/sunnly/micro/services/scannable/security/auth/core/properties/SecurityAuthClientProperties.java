package wang.sunnly.micro.services.scannable.security.auth.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * SecurityOAuthClientProperties
 *
 * @author Sunnly
 * @since 2019/6/20 13:42
 */
@EnableConfigurationProperties({SecurityAuthClientProperties.class})
@ConfigurationProperties(prefix = "sunnly.security.auth.client")
@Data
public class SecurityAuthClientProperties {

    private String id;
    private String secret;
    private String tokenHeader;

    //保存微服务pubKey
    private byte[] pubKeyByte;

}
