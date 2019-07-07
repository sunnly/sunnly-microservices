package wang.sunnly.micro.services.scannable.centre.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wang.sunnly.micro.services.scannable.centre.resource.service.ApiUserService;
import wang.sunnly.micro.services.scannable.common.core.entity.PermissionInfo;
import wang.sunnly.micro.services.scannable.common.core.entity.UserInfo;
import wang.sunnly.micro.services.scannable.security.auth.core.annotation.IgnoreClientToken;
import wang.sunnly.micro.services.scannable.security.auth.core.annotation.IgnoreUserToken;

import java.util.List;
import java.util.Map;

/**
 *
 * ApiUserController
 * @author Sunnly
 * @create 2019/6/20 0020 22:53
 **/
@RestController
@RequestMapping("api")
public class ApiUserController {

    @Autowired
    ApiUserService apiUserService;

    @GetMapping("/permissions")
    @IgnoreClientToken
    public @ResponseBody
    List<PermissionInfo> getAllPermission(){
        return apiUserService.getAllPermission();
    }

    @GetMapping("/permissions/{username}")
    @IgnoreClientToken
    public @ResponseBody
    List<PermissionInfo> getPermissionByUsername(
            @PathVariable("username") String username){
        apiUserService.validate("sunnly","sunnly");
        return apiUserService.getPermisssionByUsername(username);
    }

    @PostMapping("/user/validate")
    @IgnoreUserToken
    public @ResponseBody
    UserInfo validate(@RequestBody Map<String,String> body){
        return apiUserService.validate(body.get("username"), body.get("password"));
    }
}
