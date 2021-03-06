package wang.sunnly.micro.services.scannable.centre.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.sunnly.micro.services.scannable.centre.auth.properties.SshKeyProperties;
import wang.sunnly.micro.services.scannable.centre.auth.service.AuthClientServices;
import wang.sunnly.micro.services.scannable.common.web.msg.ObjectRestResponse;
import wang.sunnly.micro.services.scannable.security.auth.core.annotation.IgnoreClientToken;

import java.util.List;

/**
 *
 * @author Sunnly
 * @ClassName ClientController
 * @Date 2019/6/13 0013 22:45
 **/
@RestController
@RequestMapping("client")
@IgnoreClientToken
public class AuthClientController {

    @Autowired
    private AuthClientServices authClientServices;

    @Autowired
    private SshKeyProperties sshKeyProperties;
    /**
     * 根据clientId和secret换取服务端token
     * @param clientId
     * @param secret
     * @return
     */
    @PostMapping("/token")
    public ObjectRestResponse<String> getAccessToken(@RequestParam("clientId") String clientId,@RequestParam("secret")  String secret) throws Exception {
        return new ObjectRestResponse<String>().data(authClientServices.generateToken(clientId,secret));
    }

    /**
     * 获取允许哪些微服务请求我
     * @param clientId
     * @param secret
     * @return
     */
    @GetMapping("/myClient")
    public ObjectRestResponse<List<String>> getAllowClient(@RequestParam("clientId") String clientId,@RequestParam("secret")  String secret) throws Exception {
        List<String> allowClient = authClientServices.getAllowClient(clientId, secret);
//        ObjectRestResponse<String> a = getToken(clientId, secret);
        return new ObjectRestResponse<List<String>>().data(allowClient);
    }

    /**
     * 获取服务pubKey
     * @param clientId
     * @param secret
     * @return
     */
    @PostMapping("/servicePubKey")
    public ObjectRestResponse<byte[]> getServicePubKey(@RequestParam("clientId") String clientId,@RequestParam("secret")  String secret){
        //验证clientId和secret的有效性
        authClientServices.validate(clientId,secret);
        return new ObjectRestResponse<byte[]>().data(sshKeyProperties.getClientPubKey());
    }

    /**
     * 获取用户pubKey
     * @param clientId
     * @param secret
     * @return
     */
    @PostMapping("/userPubKey")
    public ObjectRestResponse<byte[]> getUserPubKey(@RequestParam("clientId") String clientId,@RequestParam("secret")  String secret){
        //验证clientId和secret的有效性
        authClientServices.validate(clientId,secret);
        return new ObjectRestResponse<byte[]>().data(sshKeyProperties.getUserPubKey());
    }

    @GetMapping("/validate")
    public boolean validateClientIdAndSecret(@RequestParam("clientId") String clientId,@RequestParam("secret")  String secret){
        try {
            authClientServices.validate(clientId,secret);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
