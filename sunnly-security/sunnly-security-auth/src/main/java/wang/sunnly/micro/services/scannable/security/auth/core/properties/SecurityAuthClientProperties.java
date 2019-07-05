package wang.sunnly.micro.services.scannable.security.auth.core.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SecurityOAuthClientProperties
 *
 * @author Sunnly
 * @create 2019/6/20 13:42
 */
//@Component
@ConfigurationProperties(prefix = "sunnly.security.auth.client")
@Data
public class SecurityAuthClientProperties {

    private String id;
    private String secret;
    private String tokenHeader;

    //保存微服务pubKey
    private byte[] pubKeyByte;

    @Value("${spring.application.name}")
    private String applicationName;
}
