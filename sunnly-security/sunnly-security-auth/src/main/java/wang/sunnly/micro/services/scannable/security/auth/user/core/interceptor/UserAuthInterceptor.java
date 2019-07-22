package wang.sunnly.micro.services.scannable.security.auth.user.core.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import wang.sunnly.micro.services.scannable.common.core.exception.SecurityInvalidException;
import wang.sunnly.micro.services.scannable.common.core.handler.BaseThreadLocalHandler;
import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;
import wang.sunnly.micro.services.scannable.security.auth.core.annotation.IgnoreUserToken;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthUserProperties;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.IJWTInfo;
import wang.sunnly.micro.services.scannable.security.auth.core.utils.tools.JWTInfoFromTokenHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * UserAuthInterceptor
 * 用户鉴权拦截器
 * @author Sunnly
 * @since 2019/6/12 0012 20:15
 **/
public class UserAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SecurityAuthUserProperties securityAuthUserProperties;

    @Autowired
    private JWTInfoFromTokenHelper jwtInfoFromTokenHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }

        String tokenKey = securityAuthUserProperties.getTokenHeader();
        if (StringUtils.isEmpty(tokenKey)){
            throw new SecurityInvalidException(SecurityInvalidStatus.USER_TOKEN_HEADER_NOT_CONFIG);
        }
        //一般在APP端或Feign接口调用是会封装到Header中
        String token = request.getHeader(tokenKey);
        //  用户直接访问一般信息放在cookie中
        if(StringUtils.isEmpty(token)){
            //从cookies中取
            if (request.getCookies() !=null){
                for (Cookie cookie : request.getCookies()){
                    if (StringUtils.equals(cookie.getName(),tokenKey)){
                        token = cookie.getValue();
                    }
                }
            }
        }
        if (StringUtils.isEmpty(token)){
            throw new SecurityInvalidException(SecurityInvalidStatus.USER_TOKEN_ERR);
//            return super.preHandle(request, response, handler);
        }

        IJWTInfo infoFromToken = jwtInfoFromTokenHelper.getInfoFromToken(token);
        //保存到本地线程存储中    ,Feign拦截器中就可以获取这些信息了
        BaseThreadLocalHandler.setUsername(infoFromToken.getUniqueName());
        BaseThreadLocalHandler.setName(infoFromToken.getName());
        BaseThreadLocalHandler.setUserID(infoFromToken.getId());
        BaseThreadLocalHandler.setToken(token);

        return super.preHandle(request, response, handler);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //处理完成后清除线程存储
        BaseThreadLocalHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
