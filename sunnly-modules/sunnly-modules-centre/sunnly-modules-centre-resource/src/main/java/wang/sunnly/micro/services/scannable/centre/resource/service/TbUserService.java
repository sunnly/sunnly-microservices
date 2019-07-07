package wang.sunnly.micro.services.scannable.centre.resource.service;

import wang.sunnly.micro.services.scannable.centre.resource.entity.TbUser;
import wang.sunnly.micro.services.scannable.centre.resource.mapper.TbUserMapper;
import wang.sunnly.micro.services.scannable.tools.mysql.service.BaseService;

public interface TbUserService extends BaseService<TbUserMapper, TbUser> {

    public TbUser getUserByUsername(String username);
}
