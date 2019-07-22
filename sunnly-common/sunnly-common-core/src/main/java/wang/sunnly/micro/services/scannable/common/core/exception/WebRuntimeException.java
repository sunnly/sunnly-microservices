package wang.sunnly.micro.services.scannable.common.core.exception;

import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;

/**
 * WebRuntimeException
 *
 * @author Sunnly
 * @since 2019/6/22 0022 16:24
 */
public class WebRuntimeException extends BaseRuntimeException {

    public WebRuntimeException(String message,int state){
        super(message,state);
    }

    public WebRuntimeException(SecurityInvalidStatus status){
        super(status.reasonPhrase(),status.value());
    }
}
