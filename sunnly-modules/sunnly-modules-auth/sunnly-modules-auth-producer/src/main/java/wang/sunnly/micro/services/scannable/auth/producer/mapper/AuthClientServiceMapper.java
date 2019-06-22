package wang.sunnly.micro.services.scannable.auth.producer.mapper;

import org.apache.ibatis.annotations.Select;
import wang.sunnly.micro.services.scannable.auth.producer.entity.AuthClientService;
import wang.sunnly.tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface AuthClientServiceMapper extends MyMapper<AuthClientService> {

    @Select("")
    List<String> getAllowedClient(String id);
}