package wang.sunnly.micro.services.scannable.centre.auth.service;

import java.util.List;

public interface AuthClientServices {


    String generateToken(String clientId, String secret) throws Exception;

    List<String> getAllowClient(String clientId, String secret);

    void validate(String clientId, String secret);

    List<String> getAllowedClient(String clientId);
}
