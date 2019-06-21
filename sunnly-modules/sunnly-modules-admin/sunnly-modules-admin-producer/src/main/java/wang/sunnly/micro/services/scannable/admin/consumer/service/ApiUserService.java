package wang.sunnly.micro.services.scannable.admin.consumer.service;

import wang.sunnly.micro.services.scannable.common.core.entity.PermissionInfo;
import wang.sunnly.micro.services.scannable.common.core.entity.UserInfo;

import java.util.List;

/**
 *
 * ApiUserService
 * @author Sunnly
 * @create  2019/6/18 15:24
 */

public interface ApiUserService {

    UserInfo validate(String username, String password);

    List<PermissionInfo> getPermisssionByUsername(String username);

    List<PermissionInfo> getAllPermission();
}
