package wang.sunnly.micro.services.scannable.admin.consumer.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.admin.consumer.entity.TbPermission;
import wang.sunnly.micro.services.scannable.admin.consumer.mapper.TbPermissionMapper;
import wang.sunnly.micro.services.scannable.admin.consumer.service.TbPermissionService;
import wang.sunnly.micro.services.scannable.common.core.entity.PermissionInfo;
import wang.sunnly.micro.services.scannable.tools.mysql.service.BaseService;
import wang.sunnly.micro.services.scannable.tools.mysql.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbPermissionServiceImpl
        extends BaseServiceImpl<TbPermissionMapper, TbPermission>
        implements TbPermissionService, BaseService<TbPermissionMapper,TbPermission> {

    @Resource
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<PermissionInfo> getPermisssionByUsername(String username) {
        return tbPermissionMapper.getPermisssionByUsername(username);
    }

    @Override
    public List<TbPermission> getAllPermission() {
        return tbPermissionMapper.getAllPermission();
    }
}
