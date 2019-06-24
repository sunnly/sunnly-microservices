package wang.sunnly.micro.services.scannable.auth.producer.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import wang.sunnly.micro.services.scannable.auth.producer.info.ClientTokenInfo;
import wang.sunnly.micro.services.scannable.auth.producer.service.AuthClientServices;
import wang.sunnly.micro.services.scannable.common.core.exception.SecurityInvalidException;
import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;
import wang.sunnly.micro.services.scannable.security.auth.core.annotation.IgnoreClientToken;
import wang.sunnly.micro.services.scannable.security.auth.core.interceptor.ClientAuthInterceptorAdapter;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.IJWTInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 鉴权服务器服务鉴权拦截器
 * ClientAuthInterceptor
 *
 * @author Sunnly
 * @create 2019/6/22 0022 10:08
 */

public class ClientResponseTokenInterceptor extends ClientAuthInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ClientResponseTokenInterceptor.class);

    @Autowired
    private AuthClientServices authClientServices;

    @Autowired
    private ClientTokenInfo clientTokenInfo;

    @Autowired
    private SecurityAuthClientProperties securityAuthClientProperties;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
        //获取IgnoreServiceToken注解
        IgnoreClientToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreClientToken.class);
        if (annotation == null){
            annotation = handlerMethod.getMethodAnnotation(IgnoreClientToken.class);
        }
        if (annotation != null){
            //配置了不需要验证的注解，直接放行
            return super.preHandle(request, response, handler);
        }

        String token = request.getHeader(securityAuthClientProperties.getTokenHeader());
        IJWTInfo infoFromToken = clientTokenInfo.getInfoFromToken(token);
        String uniqueName = infoFromToken.getUniqueName();
        if (StringUtils.isEmpty(securityAuthClientProperties.getId())){
            throw new SecurityInvalidException(SecurityInvalidStatus.CONFIG_CLIENT_ID_NULL);
        }
        for(String client: authClientServices.getAllowedClient(securityAuthClientProperties.getId())){
            if(client.equals(uniqueName)){
                return super.preHandle(request, response, handler);
            }
        }
        throw new SecurityInvalidException(SecurityInvalidStatus.CLIENT_ACCESS_DENIAL);
    }
}
