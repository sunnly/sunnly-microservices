package wang.sunnly.micro.services.scannable.centre.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.sunnly.micro.services.scannable.centre.auth.configuration.FeignRequestClientConfiguration;
import wang.sunnly.micro.services.scannable.common.core.entity.UserInfo;

import java.util.Map;

/**
 * @author Sunnly
 * @ClassName AuthUserFeign
 * @Date 2019/6/18 14:00
 * @Version 1.0
 */
@FeignClient(value = "${sunnly.feign.clients.admin:sunnly-admin}",
        configuration = FeignRequestClientConfiguration.class)
public interface AuthUserFeign {

    @PostMapping("/api/user/validate")
    public @ResponseBody
    UserInfo validate(@RequestBody Map<String, String> body);

}