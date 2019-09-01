package wang.sunnly.micro.services.scannable.tools.verification.core.service;

import org.springframework.web.context.request.ServletWebRequest;

public interface ImageValidateService {
    public boolean sender(ServletWebRequest request, String code, int width, int height);

}
