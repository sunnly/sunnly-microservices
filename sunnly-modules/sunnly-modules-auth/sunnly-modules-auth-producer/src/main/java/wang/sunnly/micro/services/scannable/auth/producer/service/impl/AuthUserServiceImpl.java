package wang.sunnly.micro.services.scannable.auth.producer.service.impl;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.auth.producer.feign.AuthUserFeign;
import wang.sunnly.micro.services.scannable.auth.producer.service.AuthUserService;
import wang.sunnly.micro.services.scannable.auth.producer.util.JWTTokenUtils;
import wang.sunnly.micro.services.scannable.common.core.entity.JWTAuthenticationUser;
import wang.sunnly.micro.services.scannable.common.core.entity.UserInfo;
import wang.sunnly.micro.services.scannable.common.core.exception.SecurityInvalidException;
import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.jwt.JWTInfo;

import java.util.Map;

/**
 * 用户相关token服务
 * @Author Sunnly
 * @create 2019年6月19日 11:12:23
 **/
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserFeign authUserFeign;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @Override
    public String getToken(JWTAuthenticationUser user) throws Exception {
        Map<String, String> map = Maps.newHashMap();
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        //验证用户
        UserInfo validate = authUserFeign.validate(map);
        if (StringUtils.isNotEmpty(validate.getId())){
            //获取token
            return jwtTokenUtils.generateToken(new JWTInfo(validate.getUsername(),validate.getId(),validate.getName()));
        }
        throw new SecurityInvalidException(SecurityInvalidStatus.USER_PASS_INVALID_CODE);
    }

    @Override
    public String refeshToken(String token) throws Exception {
        return jwtTokenUtils.generateToken(jwtTokenUtils.getInfoByToken(token));
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtils.getInfoByToken(token);
    }
}
