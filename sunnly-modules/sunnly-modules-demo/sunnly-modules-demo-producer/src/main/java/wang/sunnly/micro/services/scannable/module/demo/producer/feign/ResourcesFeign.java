package wang.sunnly.micro.services.scannable.module.demo.producer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import wang.sunnly.micro.services.scannable.common.core.entity.PermissionInfo;
import wang.sunnly.micro.services.scannable.common.core.entity.UserInfo;
import wang.sunnly.micro.services.scannable.security.auth.core.annotation.IgnoreUserToken;

import java.util.List;
import java.util.Map;

/**
 * ResourcesFeign
 *
 * @author Sunnly
 * @create 2019/6/22 0022 21:15
 */
@FeignClient(value = "sunnly-admin-producer")
public interface ResourcesFeign {

    @GetMapping("/api/permissions")
    public @ResponseBody
    List<PermissionInfo> getAllPermission();

    @GetMapping("/api/permissions/{username}")
    public @ResponseBody
    List<PermissionInfo> getPermissionByUsername(
            @PathVariable("username") String username);


    @PostMapping("/api/user/validate")
    @IgnoreUserToken
    public @ResponseBody
    UserInfo validate(@RequestBody Map<String,String> body);

}
