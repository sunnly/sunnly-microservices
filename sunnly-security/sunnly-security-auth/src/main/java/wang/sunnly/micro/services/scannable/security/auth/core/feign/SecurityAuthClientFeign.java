package wang.sunnly.micro.services.scannable.security.auth.core.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;

import java.util.List;

/**
 *
 * SecurityAuthClientFeign
 * @author Sunnly
 * @create 2019/6/21 16:26
 */
//@FeignClient(value = "${sunnly.security.auth.service-id:sunnly-auth}")
@FeignClient(value = "sunnly-auth",configuration = {})
public interface SecurityAuthClientFeign {
    @PostMapping("/client/token")
    ObjectRestResponse<String> getAccessToken(@RequestParam("clientId") String clientId,
                                              @RequestParam("secret") String secret);

    @GetMapping("/client/myClient")
    public ObjectRestResponse<List<String>> getAllowClient(@RequestParam("clientId") String clientId,
                                                           @RequestParam("secret") String secret);

    @PostMapping("/client/servicePubKey")
    public ObjectRestResponse<byte[]> getServicePubKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);

    @PostMapping("/client/userPubKey")
    public ObjectRestResponse<byte[]> getUserPubKey(@RequestParam("clientId") String clientId,
                                                    @RequestParam("secret") String secret);

}
