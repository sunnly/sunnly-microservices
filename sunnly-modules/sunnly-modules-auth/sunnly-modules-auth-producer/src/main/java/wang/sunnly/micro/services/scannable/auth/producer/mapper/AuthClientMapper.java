package wang.sunnly.micro.services.scannable.auth.producer.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wang.sunnly.micro.services.scannable.auth.producer.entity.AuthClient;
import wang.sunnly.tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface AuthClientMapper extends MyMapper<AuthClient> {

    @Select("SELECT c.CODE " +
            "FROM auth_client c " +
            "INNER JOIN auth_client_service gcs " +
            "ON gcs.client_id = c.id " +
            "WHERE gcs.service_id = #{serviceId}")
    List<String> getAllowClient(Integer id);

    @Select("SELECT client.CODE " +
            "FROM auth_client client " +
            "INNER JOIN auth_client_service gcs ON gcs.client_id = client.id " +
            "WHERE gcs.service_id = #{clientId}")
    List<String> selectAllowedClient(@Param("clientId") String clientId);
}