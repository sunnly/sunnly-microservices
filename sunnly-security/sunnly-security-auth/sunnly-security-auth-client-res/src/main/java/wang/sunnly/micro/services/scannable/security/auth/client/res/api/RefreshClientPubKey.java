package wang.sunnly.micro.services.scannable.security.auth.client.res.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import wang.sunnly.micro.services.scannable.common.web.msg.BaseResponse;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.security.auth.core.feign.SecurityAuthClientFeign;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;

/**
 * RefreshPubKey
 *
 * @author Sunnly
 * @create 2019/6/22 0022 2:41
 */
@Component
public class RefreshClientPubKey {

    @Autowired
    private SecurityAuthClientFeign securityAuthClientFeign;

    @Autowired
    private SecurityAuthClientProperties securityAuthClientProperties;

    public void refreshClientPubKey(){
        BaseResponse resp = securityAuthClientFeign.getServicePubKey(securityAuthClientProperties.getId(),
                securityAuthClientProperties.getSecret());
        if (resp.getStatus() == HttpStatus.OK.value()) {
            ObjectRestResponse<byte[]> userResponse = (ObjectRestResponse<byte[]>) resp;
            securityAuthClientProperties.setPubKeyByte(userResponse.getData());
        }
    }
}