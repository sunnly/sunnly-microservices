package wang.sunnly.micro.services.scannable.module.demo.producer.controller;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.micro.services.scannable.common.core.entity.PermissionInfo;
import wang.sunnly.micro.services.scannable.common.core.entity.UserInfo;
import wang.sunnly.micro.services.scannable.module.demo.producer.feign.ResourcesFeign;
import wang.sunnly.micro.services.scannable.security.auth.core.annotation.IgnoreClientToken;
import wang.sunnly.micro.services.scannable.security.auth.core.annotation.IgnoreUserToken;
import wang.sunnly.micro.services.scannable.tools.logger.annotation.SunnlyLogger;

import java.util.List;
import java.util.Map;

/**
 * ProducerTestController
 *
 * @author Sunnly
 * @since 2019/6/21 14:43
 */
@RestController
@RequestMapping("r")
public class ResourcesController {

    @Autowired
    private ResourcesFeign resourcesFeign;

    @GetMapping("/all")
    public List<PermissionInfo> getToken() {
        return resourcesFeign.getAllPermission();
    }

    @GetMapping("/by")
    @SunnlyLogger("获取用户权限")
    @IgnoreClientToken
    public List<PermissionInfo> me(@RequestParam("username") String username) {
        return resourcesFeign.getPermissionByUsername(username);
    }


    @GetMapping("/jt")
    @IgnoreUserToken
    public UserInfo jt(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        Map map = Maps.newHashMap();
        map.put("username", username);
        map.put("password", password);
        return resourcesFeign.validate(map);
    }


}
