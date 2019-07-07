package wang.sunnly.micro.services.scannable.security.auth.core.utils.tools;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.sunnly.micro.services.scannable.common.core.exception.SecurityInvalidException;
import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthUserProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.IJWTInfo;

import java.security.SignatureException;

//@Component
public class JWTInfoFromTokenHelper {

    @Autowired
    private SecurityAuthUserProperties securityAuthUserProperties;
    /**
     * 通过token换取服务信息
     * @param token token
     * @return IJWTInfo
     */
    public IJWTInfo getInfoFromToken(String token){
        try {
            return JWTHelper.getInfoFromToken(token,
                    securityAuthUserProperties.getPubKeyByte());
        } catch (ExpiredJwtException ex) {
            throw new SecurityInvalidException(SecurityInvalidStatus.USER_TOKEN_EXPIRED);
        } catch (SignatureException ex) {
            throw new SecurityInvalidException(SecurityInvalidStatus.USER_TOKEN_SIGNATURE_ERROR);
        } catch (IllegalArgumentException ex) {
            throw new SecurityInvalidException(SecurityInvalidStatus.USER_TOKEN_EMPTY);
        }catch (Exception ex){
            throw new SecurityInvalidException(SecurityInvalidStatus.USER_TOKEN_FORBIDDEN);
        }
    }
}