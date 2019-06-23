package wang.sunnly.micro.services.scannable.auth.producer.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * 这个类专用于鉴权服务器
 * SshKeyProperties
 * @author Sunnly
 * @create 2019/6/21 12:00
 */
@Data
@Configurable
@Component
public class SshKeyProperties {

    @Value("${sunnly.security.auth.user.rsa-secret}")
    private String userSecret;
    @Value("${sunnly.security.auth.client.rsa-secret}")
    private String clientSecret;
    private byte[] userPubKey;
    private byte[] userPriKey;
    private byte[] clientPriKey;
    private byte[] clientPubKey;
}
