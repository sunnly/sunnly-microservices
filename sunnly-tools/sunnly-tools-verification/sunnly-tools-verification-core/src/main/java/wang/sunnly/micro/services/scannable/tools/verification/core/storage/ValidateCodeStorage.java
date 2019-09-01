package wang.sunnly.micro.services.scannable.tools.verification.core.storage;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeStorage {

    public void save(ServletWebRequest request, String key, String id, String code, int expire);

    public boolean validate(ServletWebRequest request, String key, String id, String code);

    public boolean validateAnyIgnoreCase(ServletWebRequest request, String key, String id, String code);

    boolean validate(ServletWebRequest request, String key, String id, String code, boolean clear);

    boolean validateAnyIgnoreCase(ServletWebRequest request, String key, String id, String code, boolean clear);
}
