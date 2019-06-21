package wang.sunnly.micro.services.scannable.common.core.exception;

import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;

public class SecurityInvalidException extends BaseRuntimeException {

    public SecurityInvalidException(SecurityInvalidStatus invalidStatus){
        super(invalidStatus.reasonPhrase(),invalidStatus.value());
    }
}
