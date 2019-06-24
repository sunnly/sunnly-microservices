package wang.sunnly.micro.services.scannable.module.demo.consumer.service;

import com.google.common.collect.Maps;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wang.sunnly.micro.services.scannable.common.core.entity.JWTAuthenticationUser;
import wang.sunnly.micro.services.scannable.common.core.entity.PermissionInfo;
import wang.sunnly.micro.services.scannable.common.core.entity.UserInfo;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.security.auth.core.annotation.IgnoreUserToken;

import java.util.List;
import java.util.Map;

/**
 * TestDemoProducerFeign
 *
 * @author Sunnly
 * @create 2019/6/24 0024 0:06
 */
@FeignClient("${sunnly.feign.clients.demo:sunnly-demo}")
public interface TestDemoProducerFeign {

    @GetMapping("/test/token")
    public ObjectRestResponse<String> getToken(@RequestParam("clientId") String clientId, @RequestParam("secret")  String secret) throws Exception;

    @GetMapping("/test/me")
    public ObjectRestResponse<List<String>> me(@RequestParam("clientId") String clientId, @RequestParam("secret")  String secret) throws Exception;

//    @GetMapping("/test/jt")
//    @IgnoreUserToken
//    public ObjectRestResponse<String> jt(@RequestParam("username") String username, @RequestParam("password")  String password);


    @GetMapping("/test/jr")
    public ObjectRestResponse<String> jr() throws Exception;



    @GetMapping("/r/all")
    public List<PermissionInfo> getToken();

    @GetMapping("/r/by")
    public List<PermissionInfo> me(@RequestParam("username") String username);


    @GetMapping("/jt")
    @IgnoreUserToken
    public UserInfo jt(@RequestParam("username") String username, @RequestParam("password")  String password) throws Exception;
}
