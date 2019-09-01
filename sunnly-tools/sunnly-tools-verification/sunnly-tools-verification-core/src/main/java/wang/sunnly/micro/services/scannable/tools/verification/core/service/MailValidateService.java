package wang.sunnly.micro.services.scannable.tools.verification.core.service;

import org.springframework.web.context.request.ServletWebRequest;

public interface MailValidateService {
    public boolean sender(ServletWebRequest request, String mail, String code);
}
