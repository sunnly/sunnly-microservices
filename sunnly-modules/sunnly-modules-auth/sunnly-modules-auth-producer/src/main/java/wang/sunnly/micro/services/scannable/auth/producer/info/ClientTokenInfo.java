package wang.sunnly.micro.services.scannable.auth.producer.info;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.sunnly.micro.services.scannable.auth.producer.properties.SshKeyProperties;
import wang.sunnly.micro.services.scannable.common.core.exception.SecurityInvalidException;
import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.IJWTInfo;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.tools.JWTHelper;

import java.security.SignatureException;

/**
 * ClientTokenInfo
 *
 * @author Sunnly
 * @create 2019/6/22 0022 18:52
 */
@Component
public class ClientTokenInfo {

    @Autowired
    private SshKeyProperties sshKeyProperties;

    /**
     * 通过token换取服务信息
     * @param token token
     * @return IJWTInfo
     */
    public IJWTInfo getInfoFromToken(String token) {
        try {
            return JWTHelper.getInfoFromToken(token,
                    sshKeyProperties.getClientPubKey());
        } catch (ExpiredJwtException ex) {
            throw new SecurityInvalidException(SecurityInvalidStatus.CLIENT_TOKEN_EXPIRED);
        } catch (SignatureException ex) {
            throw new SecurityInvalidException(SecurityInvalidStatus.CLIENT_TOKEN_SIGNATURE_ERROR);
        } catch (IllegalArgumentException ex) {
            throw new SecurityInvalidException(SecurityInvalidStatus.CLIENT_TOKEN_EMPTY);
        } catch (Exception ex) {
            throw new SecurityInvalidException(SecurityInvalidStatus.CLIENT_FORBIDDEN);
        }
    }
}
