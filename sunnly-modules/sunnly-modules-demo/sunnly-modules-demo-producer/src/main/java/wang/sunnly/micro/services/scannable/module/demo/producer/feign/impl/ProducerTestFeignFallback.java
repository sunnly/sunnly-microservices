package wang.sunnly.micro.services.scannable.module.demo.producer.feign.impl;

import com.sun.org.apache.bcel.internal.generic.Select;
import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.common.core.entity.JWTAuthenticationUser;
import wang.sunnly.micro.services.scannable.common.core.exception.SecurityInvalidException;
import wang.sunnly.micro.services.scannable.common.core.exception.WebRuntimeException;
import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.module.demo.producer.feign.ProducerTestFeign;

import java.util.List;

/**
 * ProducerTestFeignFallback
 *
 * @author Sunnly
 * @create 2019/6/21 14:53
 */
@Service
public class ProducerTestFeignFallback implements ProducerTestFeign {

    @Override
    public ObjectRestResponse<String> getAccessToken(String clientId, String secret) {
        return null;
    }

    @Override
    public ObjectRestResponse<List<String>> getAllowClient(String clientId, String secret) {
        throw new WebRuntimeException(SecurityInvalidStatus.NETWORK_CONNECTION_ERR);
    }

    @Override
    public ObjectRestResponse<byte[]> getServicePubKey(String clientId, String secret) {
        return null;
    }

    @Override
    public ObjectRestResponse<byte[]> getUserPubKey(String clientId, String secret) {
        return null;
    }

    @Override
    public boolean validateClientIdAndSecret(String clientId, String secret) {
        return false;
    }

    @Override
    public ObjectRestResponse<String> getToken(JWTAuthenticationUser user) throws Exception {
        return null;
    }

    @Override
    public ObjectRestResponse<String> refreshToken() throws Exception {
        return null;
    }
}
