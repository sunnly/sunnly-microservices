package wang.sunnly.micro.services.scannable.security.auth.core.feign.impl;

import org.springframework.stereotype.Component;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.security.auth.core.feign.SecurityAuthClientFeign;

import java.util.List;

/**
 * @author Sunnly
 * @ClassName SecurityAuthServiceFeignFallback
 * @Date 2019/6/16 0016 23:38
 **/
@Component
public class SecurityAuthClientFeignFallback implements SecurityAuthClientFeign {
    @Override
    public ObjectRestResponse<String> getAccessToken(String clientId, String secret) {
        return null;
    }

    @Override
    public ObjectRestResponse<List<String>> getAllowClient(String clientId, String secret) {
        return null;
    }

    @Override
    public ObjectRestResponse<byte[]> getServicePubKey(String clientId, String secret) {
        return null;
    }

    @Override
    public ObjectRestResponse<byte[]> getUserPubKey(String clientId, String secret) {
        return null;
    }

}
