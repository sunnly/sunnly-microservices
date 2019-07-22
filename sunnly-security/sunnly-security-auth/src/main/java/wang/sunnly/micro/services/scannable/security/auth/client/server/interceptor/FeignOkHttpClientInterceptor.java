package wang.sunnly.micro.services.scannable.security.auth.client.server.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import wang.sunnly.micro.services.scannable.common.core.status.SecurityInvalidStatus;
import wang.sunnly.micro.services.scannable.security.auth.client.core.store.ClientTokenStore;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;

import java.io.IOException;

/**
 * FeignOkHttpClientInterceptor
 * 拦截Feign请求，在header中加入token，
 * 服务器端才可以获取到用户鉴权和服务鉴权
 * @author Sunnly
 * @since 2019/6/17 16:51
 */
public class FeignOkHttpClientInterceptor implements Interceptor {

    @Autowired
    @Lazy
    private SecurityAuthClientProperties securityAuthClientProperties;

    @Autowired
    @Lazy
    private ClientTokenStore clientTokenStore;

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (chain.request().url().toString().contains("client/token")){
            return chain.proceed(chain.request());
        }
        Request request = chain.request().newBuilder()
            .header(securityAuthClientProperties.getTokenHeader(),
                    clientTokenStore.getClientToken())
            .build();
        Response response = chain.proceed(request);

        if(HttpStatus.FORBIDDEN.value() == response.code()){
            //微服务token已过期，刷新token重新请求
            if (response.body().string().contains(SecurityInvalidStatus.CLIENT_FORBIDDEN.value()+"")) {
                clientTokenStore.refreshClientToken();
                request = chain.request()
                        .newBuilder()
                        .header(securityAuthClientProperties.getTokenHeader(),
                                clientTokenStore.getClientToken())

                        .build();
                response = chain.proceed(request);
            }
        }
        return response;
    }
}
