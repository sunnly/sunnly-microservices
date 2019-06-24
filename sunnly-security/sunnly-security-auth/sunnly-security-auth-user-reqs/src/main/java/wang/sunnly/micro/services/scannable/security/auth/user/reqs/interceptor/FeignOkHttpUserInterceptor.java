package wang.sunnly.micro.services.scannable.security.auth.user.reqs.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import wang.sunnly.micro.services.scannable.common.core.handler.BaseThreadLocalHandler;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthUserProperties;

import java.io.IOException;

/**
 * 拦截Feign请求，在header中加入token，
 * 服务器端才可以获取到用户鉴权和服务鉴权
 * FeignOkHttpUserInterceptor
 * @author Sunnly
 * @create 2019/6/21 16:29
 */
@Component
public class FeignOkHttpUserInterceptor implements Interceptor {

    @Autowired
    @Lazy
    private SecurityAuthUserProperties securityAuthUserProperties;

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request().newBuilder()
            .header(securityAuthUserProperties.getTokenHeader(),
                    BaseThreadLocalHandler.getToken())
            .build();
        return chain.proceed(request);
    }
}
