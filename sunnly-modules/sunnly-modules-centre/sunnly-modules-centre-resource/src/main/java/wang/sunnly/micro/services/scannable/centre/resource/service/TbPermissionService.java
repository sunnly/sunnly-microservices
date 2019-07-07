package wang.sunnly.micro.services.scannable.centre.resource.service;

import wang.sunnly.micro.services.scannable.centre.resource.entity.TbPermission;
import wang.sunnly.micro.services.scannable.centre.resource.mapper.TbPermissionMapper;
import wang.sunnly.micro.services.scannable.common.core.entity.PermissionInfo;
import wang.sunnly.micro.services.scannable.tools.mysql.service.BaseService;

import java.util.List;

public interface TbPermissionService extends BaseService<TbPermissionMapper,TbPermission> {

    List<TbPermission> getPermisssionByUsername(String username);

    List<TbPermission> getAllPermission();
}
