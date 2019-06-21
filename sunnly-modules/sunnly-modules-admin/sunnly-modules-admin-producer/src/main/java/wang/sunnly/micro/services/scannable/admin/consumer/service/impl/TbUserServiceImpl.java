package wang.sunnly.micro.services.scannable.admin.consumer.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.admin.consumer.entity.TbUser;
import wang.sunnly.micro.services.scannable.admin.consumer.mapper.TbUserMapper;
import wang.sunnly.micro.services.scannable.admin.consumer.service.TbUserService;
import wang.sunnly.micro.services.scannable.tools.mysql.service.BaseService;
import wang.sunnly.micro.services.scannable.tools.mysql.service.impl.BaseServiceImpl;

@Service
public class TbUserServiceImpl
        extends BaseServiceImpl<TbUserMapper,TbUser>
        implements TbUserService,BaseService<TbUserMapper,TbUser> {

    @Override
    public TbUser getUserByUsername(String username) {
        return mapper.getUserByUsername(username);
    }
}
