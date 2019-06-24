package wang.sunnly.micro.services.scannable.auth.producer.service.impl;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.auth.producer.entity.AuthClient;
import wang.sunnly.micro.services.scannable.auth.producer.mapper.AuthClientMapper;
import wang.sunnly.micro.services.scannable.auth.producer.properties.SshKeyProperties;
import wang.sunnly.micro.services.scannable.auth.producer.service.AuthClientServices;
import wang.sunnly.micro.services.scannable.common.core.exception.SecurityInvalidException;
import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.client.ClientInfo;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.tools.JWTHelper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuthClientServicesImpl implements AuthClientServices {

    @Resource
    private AuthClientMapper authClientMapper;

    //生成jwt 时的有效时长
    @Value("${sunnly.security.auth.client.expire:3600}")
    private int expire = 3600;

    @Autowired
    private SshKeyProperties sshKeyProperties;

    @Override
    public String generateToken(String clientId, String secret) throws Exception {
        //验证clientId和secret的合法性
        AuthClient client = getClient(clientId, secret);
        //生成token
        return JWTHelper.generateToken(new ClientInfo(client.getCode(),client.getName(),client.getId().toString()),
                sshKeyProperties.getClientPriKey(),expire);
    }

    @Override
    public List<String> getAllowClient(String clientId, String secret) {
        AuthClient client = getClient(clientId, secret);
        List<String> allowClient = authClientMapper.getAllowClient(client.getId());
        return allowClient == null ? Lists.<String>newArrayList() : allowClient;
    }

    @Override
    public void validate(String clientId, String secret){
        getClient(clientId,secret);
    }

    private AuthClient getClient(String clientId, String secret){
        if (StringUtils.isEmpty(clientId)|| StringUtils.isEmpty(secret)){
            throw new SecurityInvalidException(SecurityInvalidStatus.CLIENT_OR_SECRENT_ERROR);
        }
        AuthClient client = new AuthClient();
        client.setCode(clientId);
        client = authClientMapper.selectOne(client);
        if (client == null || !StringUtils.equals(client.getSecret(),secret)){
            //clientId和secret非法
            throw new SecurityInvalidException(SecurityInvalidStatus.CLIENT_OR_SECRENT_ERROR);
        }
        return client;
    }

    @Override
    public List<String> getAllowedClient(String clientId) {
        AuthClient info = getClient(clientId);
        if (Objects.isNull(info)){
            throw new SecurityInvalidException(SecurityInvalidStatus.CLIENT_INFO_ERR);
        }
        List<String> clients = authClientMapper.selectAllowedClient(info.getId() + "");
        if(clients==null) {
            new ArrayList<String>();
        }
        return clients;
    }

    private AuthClient getClient(String clientId) {
        AuthClient client = new AuthClient();
        client.setCode(clientId);
        client = authClientMapper.selectOne(client);
        return client;
    }
}
