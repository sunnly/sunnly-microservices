package wang.sunnly.micro.services.scannable.security.core.exception;

import wang.sunnly.micro.services.scannable.common.core.exception.BaseRuntimeException;
import wang.sunnly.micro.services.scannable.security.core.status.SecurityInvalidStatus;

public class SecurityInvalidException extends BaseRuntimeException {

    public  SecurityInvalidException(SecurityInvalidStatus invalidStatus){
        super(invalidStatus.reasonPhrase(),invalidStatus.value());
    }
}
