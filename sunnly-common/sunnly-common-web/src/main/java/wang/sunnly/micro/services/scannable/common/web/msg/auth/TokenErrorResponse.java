package wang.sunnly.micro.services.scannable.common.web.msg.auth;

import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;
import wang.sunnly.micro.services.scannable.common.web.msg.BaseResponse;

/**
 * Created by ace on 2017/8/23.
 */
public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(SecurityInvalidStatus.USER_TOKEN_ERROR_CODE.value(), message);
    }

    public TokenErrorResponse(SecurityInvalidStatus status){
        super(status.value(),status.reasonPhrase());
    }

    public TokenErrorResponse(){
        super(SecurityInvalidStatus.USER_TOKEN_ERROR_CODE.value(),SecurityInvalidStatus.USER_TOKEN_ERROR_CODE.reasonPhrase());
    }
}
