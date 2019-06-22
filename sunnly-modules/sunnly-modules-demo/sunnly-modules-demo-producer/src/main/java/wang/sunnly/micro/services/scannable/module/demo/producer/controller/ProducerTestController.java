package wang.sunnly.micro.services.scannable.module.demo.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.sunnly.micro.services.scannable.common.core.entity.JWTAuthenticationUser;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.module.demo.producer.feign.ProducerTestFeign;

import java.util.List;

/**
 * ProducerTestController
 *
 * @author Sunnly
 * @create 2019/6/21 14:43
 */
@RestController
@RequestMapping("test")
public class ProducerTestController {

    @Autowired
    private ProducerTestFeign producerTestFeign;

    @GetMapping("/token")
    public ObjectRestResponse<String> getToken(@RequestParam("clientId") String clientId, @RequestParam("secret")  String secret) throws Exception {
        return producerTestFeign.getAccessToken(clientId,secret);
    }

    @GetMapping("/me")
    public ObjectRestResponse<List<String>> me(@RequestParam("clientId") String clientId, @RequestParam("secret")  String secret) throws Exception {
        return producerTestFeign.getAllowClient(clientId,secret);
    }


    @GetMapping("/jt")
    public ObjectRestResponse<String> jt(@RequestParam("username") String username, @RequestParam("password")  String password) throws Exception {
        JWTAuthenticationUser jwtuser = new JWTAuthenticationUser();
        jwtuser.setUsername(username);
        jwtuser.setPassword(password);
        return producerTestFeign.getToken(jwtuser);
    }


    @GetMapping("/jr")
    public ObjectRestResponse<String> jr() throws Exception {
        return producerTestFeign.refreshToken();
    }

}
