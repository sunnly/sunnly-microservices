package wang.sunnly.micro.services.scannable.admin.consumer.service;

import wang.sunnly.micro.services.scannable.admin.consumer.entity.TbUser;
import wang.sunnly.micro.services.scannable.admin.consumer.mapper.TbUserMapper;
import wang.sunnly.micro.services.scannable.tools.mysql.service.BaseService;

public interface TbUserService extends BaseService<TbUserMapper,TbUser> {

    public TbUser getUserByUsername(String username);
}
