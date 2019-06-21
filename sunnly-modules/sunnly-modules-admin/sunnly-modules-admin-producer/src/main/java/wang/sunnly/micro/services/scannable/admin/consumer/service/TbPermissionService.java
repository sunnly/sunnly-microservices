package wang.sunnly.micro.services.scannable.admin.consumer.service;

import wang.sunnly.micro.services.scannable.admin.consumer.entity.TbPermission;
import wang.sunnly.micro.services.scannable.admin.consumer.mapper.TbPermissionMapper;
import wang.sunnly.micro.services.scannable.common.core.entity.PermissionInfo;
import wang.sunnly.micro.services.scannable.tools.mysql.service.BaseService;

import java.util.List;

public interface TbPermissionService extends BaseService<TbPermissionMapper,TbPermission> {

    List<PermissionInfo> getPermisssionByUsername(String username);

    List<TbPermission> getAllPermission();
}
