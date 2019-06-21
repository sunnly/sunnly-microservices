package wang.sunnly.micro.services.scannable.security.auth.core.utils;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.jwt.IJWTInfo;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.jwt.JWTHelper;
import wang.sunnly.micro.services.scannable.security.core.exception.SecurityInvalidException;
import wang.sunnly.micro.services.scannable.security.core.status.SecurityInvalidStatus;

import java.security.SignatureException;

@Component
public class ClientTokenHelper {

    @Autowired
    private SecurityAuthClientProperties securityAuthClientProperties;
    /**
     * 通过token换取服务信息
     * @param token token
     * @return IJWTInfo
     */
    public IJWTInfo getInfoFromToken(String token) {
        try {
            return JWTHelper.getInfoFromToken(token,
                    securityAuthClientProperties.getPubKeyByte());
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
