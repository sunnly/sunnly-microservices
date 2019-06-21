package wang.sunnly.micro.services.scannable.module.demo.producer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;

import java.util.List;

/**
 * ProducerTestFeign
 *
 * @author Sunnly
 * @create 2019/6/21 14:49
 */
@FeignClient("sunnly-auth-producer")
public interface ProducerTestFeign {

    @PostMapping("/client/token")
    public ObjectRestResponse<String> getAccessToken(@RequestParam("clientId") String clientId,@RequestParam("secret")  String secret);

    @GetMapping("/client/myClient")
    public ObjectRestResponse<List<String>> getAllowClient(@RequestParam("clientId") String clientId, @RequestParam("secret")  String secret);

    @PostMapping("/client/servicePubKey")
    public ObjectRestResponse<byte[]> getServicePubKey(@RequestParam("clientId") String clientId,@RequestParam("secret")  String secret);

    @PostMapping("/client/userPubKey")
    public ObjectRestResponse<byte[]> getUserPubKey(@RequestParam("clientId") String clientId, @RequestParam("secret")  String secret);

    @GetMapping("/client/validate")
    public boolean validateClientIdAndSecret(@RequestParam("clientId") String clientId,@RequestParam("secret")  String secret);

}
