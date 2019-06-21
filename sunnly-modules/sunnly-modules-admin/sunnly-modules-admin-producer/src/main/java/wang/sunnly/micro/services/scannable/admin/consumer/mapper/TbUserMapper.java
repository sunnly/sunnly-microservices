package wang.sunnly.micro.services.scannable.admin.consumer.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import wang.sunnly.micro.services.scannable.admin.consumer.entity.TbUser;
import wang.sunnly.tk.mybatis.mapper.MyMapper;

@Repository
public interface TbUserMapper extends MyMapper<TbUser> {

    @Select("SELECT * FROM tb_user WHERE username=#{username}")
    TbUser getUserByUsername(@Param("username") String username);
}