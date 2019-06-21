package wang.sunnly.micro.services.scannable.common.web.msg.auth;

import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;
import wang.sunnly.micro.services.scannable.common.web.msg.BaseResponse;

/**
 * Created by ace on 2017/8/25.
 */
public class TokenForbiddenResponse  extends BaseResponse {
    public TokenForbiddenResponse(String message) {
        super(SecurityInvalidStatus.USER_TOKEN_FORBIDDEN.value(), message);
    }
    public TokenForbiddenResponse(SecurityInvalidStatus status) {
        super(status.value(),status.reasonPhrase());
    }
    public TokenForbiddenResponse() {
        super(SecurityInvalidStatus.USER_TOKEN_FORBIDDEN.value(),SecurityInvalidStatus.USER_TOKEN_FORBIDDEN.reasonPhrase());
    }

}
