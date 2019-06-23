package wang.sunnly.micro.services.scannable.security.auth.user.core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import wang.sunnly.micro.services.scannable.common.web.msg.BaseResponse;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.security.auth.core.feign.SecurityAuthClientFeign;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthUserProperties;

/**
 * RefreshPubKey
 *
 * @author Sunnly
 * @create 2019/6/22 0022 2:41
 */
@Component
public class RefreshUserPubKey {

    @Autowired
    private SecurityAuthClientFeign securityAuthClientFeign;

    @Autowired
    private SecurityAuthClientProperties securityAuthClientProperties;

    @Autowired
    private SecurityAuthUserProperties securityAuthUserProperties;

//    TODO 调试阶段暂时
//    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserPubKey(){
        BaseResponse resp = securityAuthClientFeign.getUserPubKey(securityAuthClientProperties.getId(),
                securityAuthClientProperties.getSecret());
        if (resp.getStatus() == HttpStatus.OK.value()) {
            ObjectRestResponse<byte[]> userResponse = (ObjectRestResponse<byte[]>) resp;
            securityAuthUserProperties.setPubKeyByte(userResponse.getData());
        }
    }

}