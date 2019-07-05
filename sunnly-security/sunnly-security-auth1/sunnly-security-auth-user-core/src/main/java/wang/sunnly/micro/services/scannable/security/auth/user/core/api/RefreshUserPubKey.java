package wang.sunnly.micro.services.scannable.security.auth.user.core.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityAuthClientFeign securityAuthClientFeign;

    @Autowired
    private SecurityAuthClientProperties securityAuthClientProperties;

    @Autowired
    private SecurityAuthUserProperties securityAuthUserProperties;

    public void refreshUserPubKey(){
        logger.info("【RefreshUserPubKey：refreshUserPubKey】开始刷新UserPubKey");
        BaseResponse resp = securityAuthClientFeign.getUserPubKey(securityAuthClientProperties.getId(),
                securityAuthClientProperties.getSecret());
        if (resp.getStatus() == HttpStatus.OK.value()) {
            ObjectRestResponse<byte[]> userResponse = (ObjectRestResponse<byte[]>) resp;
            securityAuthUserProperties.setPubKeyByte(userResponse.getData());
        }
        logger.info("【RefreshUserPubKey：refreshUserPubKey】刷新UserPubKey结束");
    }

}
