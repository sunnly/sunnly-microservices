package wang.sunnly.micro.services.scannable.centre.auth.service;

import wang.sunnly.micro.services.scannable.common.core.entity.JWTAuthenticationUser;

/**
 * @author Sunnly
 * @ClassName AuthUserService
 * @Date 2019/6/18 13:54
 * @Version 1.0
 */
public interface AuthUserService {

    public String getToken(JWTAuthenticationUser user) throws Exception;

    String refeshToken(String token) throws Exception;

    void validate(String token) throws Exception;
}
