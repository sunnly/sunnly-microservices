package wang.sunnly.micro.services.scannable.centre.auth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wang.sunnly.micro.services.scannable.centre.auth.properties.SshKeyProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.IJWTInfo;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.tools.JWTHelper;

/**
 * JWTTokenUtils
 *
 * @author Sunnly
 * @since 2019/6/19 10:27
 */
@Component
public class JWTTokenUtils {

    @Autowired
    private SshKeyProperties sshKeyProperties;

    @Value("${sunnly.security.auth.user.expire: 1200}")
    private int expire;


    public String generateToken(IJWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateToken(jwtInfo,sshKeyProperties.getUserPriKey(),expire);
    }

    public IJWTInfo getInfoByToken(String token) throws Exception {
        return JWTHelper.getInfoFromToken(token,sshKeyProperties.getUserPubKey());
    }
}
