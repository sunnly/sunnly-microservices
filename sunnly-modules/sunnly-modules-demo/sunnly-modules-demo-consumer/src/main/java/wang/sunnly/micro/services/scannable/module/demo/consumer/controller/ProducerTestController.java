package wang.sunnly.micro.services.scannable.module.demo.consumer.controller;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.micro.services.scannable.common.core.entity.PermissionInfo;
import wang.sunnly.micro.services.scannable.common.core.entity.UserInfo;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.module.demo.consumer.service.TestDemoProducerFeign;
import wang.sunnly.micro.services.scannable.security.auth.core.annotation.IgnoreUserToken;

import java.util.List;
import java.util.Map;

/**
 * ProducerTestController
 *
 * @author Sunnly
 * @create 2019/6/21 14:43
 */
@RestController
@RequestMapping("p")
public class ProducerTestController {

    @Autowired
    private TestDemoProducerFeign testDemoProducerFeign;

    @GetMapping("/token")
    public ObjectRestResponse<String> getToken(@RequestParam("clientId") String clientId, @RequestParam("secret")  String secret) throws Exception {
        return testDemoProducerFeign.getToken(clientId,secret);
    }

    @GetMapping("/me")
    public ObjectRestResponse<List<String>> me(@RequestParam("clientId") String clientId, @RequestParam("secret")  String secret) throws Exception {
        return testDemoProducerFeign.me(clientId,secret);
    }

//    @GetMapping("/jt")
//    @IgnoreUserToken
//    public ObjectRestResponse<String> jt(@RequestParam("username") String username, @RequestParam("password")  String password) throws Exception {
//        JWTAuthenticationUser jwtuser = new JWTAuthenticationUser();
//        jwtuser.setUsername(username);
//        jwtuser.setPassword(password);
//        return producerTestFeign.getToken(jwtuser);
//    }


    @GetMapping("/jr")
    public ObjectRestResponse<String> jr() throws Exception {
        return testDemoProducerFeign.jr();
    }

    @GetMapping("/all")
    public List<PermissionInfo> getToken() {
        return testDemoProducerFeign.getToken();
    }

    @GetMapping("/by")
    public List<PermissionInfo> me(@RequestParam("username") String username) {
        return testDemoProducerFeign.me(username);
    }


    @GetMapping("/jt")
    @IgnoreUserToken
    public UserInfo jt(@RequestParam("username") String username, @RequestParam("password")  String password) throws Exception {
        Map map = Maps.newHashMap();
        map.put("username",username);
        map.put("password",password);
        return testDemoProducerFeign.jt(username,password);
    }

}
