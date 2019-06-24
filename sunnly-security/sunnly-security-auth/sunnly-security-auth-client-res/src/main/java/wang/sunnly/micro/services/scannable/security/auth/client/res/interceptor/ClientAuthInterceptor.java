package wang.sunnly.micro.services.scannable.security.auth.client.res.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import wang.sunnly.micro.services.scannable.common.core.exception.SecurityInvalidException;
import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;
import wang.sunnly.micro.services.scannable.security.auth.clietn.core.store.ClientTokenStore;
import wang.sunnly.micro.services.scannable.security.auth.core.annotation.IgnoreClientToken;
import wang.sunnly.micro.services.scannable.security.auth.core.interceptor.ClientAuthInterceptorAdapter;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.IJWTInfo;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.tools.ClientInfoFromTokenHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 服务鉴权拦截器
 * @author Sunnly
 * @ClassName ServiceAuthRestInterceptor
 * @Date 2019/6/12 0012 20:15
 **/
public class ClientAuthInterceptor extends ClientAuthInterceptorAdapter {

    @Autowired
    private ClientTokenStore clientTokenStore;

    @Autowired
    private ClientInfoFromTokenHelper clientInfoFromTokenHelper;

    @Autowired
    private SecurityAuthClientProperties securityAuthClientProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //获取IgnoreClientToken注解
        IgnoreClientToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreClientToken.class);
        if (annotation == null){
            annotation = handlerMethod.getMethodAnnotation(IgnoreClientToken.class);
        }
        if (annotation != null){
            //配置了不需要验证的注解，直接放行
            return super.preHandle(request, response, handler);
        }
        //未配置忽略注解，判断是否在配置中进行
        //获取header中的token,key值从配置文件sunnly.security.auth.client.token-header中获取
        String tokenKey = securityAuthClientProperties.getTokenHeader();
        if (StringUtils.isEmpty(tokenKey)){
            throw new SecurityInvalidException(SecurityInvalidStatus.CLIENT_TOKEN_HEADER_NOT_CONFIG);
        }
        //获取请求头中的token,当服务鉴权时，Feign请求时将携带自己的token过来请求
        String token = request.getHeader(tokenKey);

        if(StringUtils.isEmpty(token)){
            throw new SecurityInvalidException(SecurityInvalidStatus.CLIENT_TOKEN_EMPTY);
        }
        //解析请求头中的token，服务端token需要通过公钥解析，从鉴权服务器获取公钥
        IJWTInfo infoFromToken = clientInfoFromTokenHelper.getInfoFromToken(token);
        String uniqueName = infoFromToken.getUniqueName();
        for (String client : clientTokenStore.getAllowedClient()){
            if (StringUtils.equals(client, uniqueName)){
                return super.preHandle(request, response, handler);
            }
        }
        throw new SecurityInvalidException(SecurityInvalidStatus.CLIENT_ACCESS_DENIAL);
    }

}
