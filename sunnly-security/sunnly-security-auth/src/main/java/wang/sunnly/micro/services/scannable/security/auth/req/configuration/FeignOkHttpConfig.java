package wang.sunnly.micro.services.scannable.security.auth.req.configuration;

import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import wang.sunnly.micro.services.scannable.security.auth.client.server.interceptor.FeignOkHttpClientInterceptor;
import wang.sunnly.micro.services.scannable.security.auth.user.reqs.interceptor.FeignOkHttpUserInterceptor;

import java.util.concurrent.TimeUnit;

/**
 *
 * FeignOkHttpClientConfig
 * @author Sunnly
 * @create 2019/6/21 16:05
 */

@AutoConfigureBefore(FeignAutoConfiguration.class)
@Configurable
@ConditionalOnClass(Feign.class)
public class FeignOkHttpConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    private FeignOkHttpClientInterceptor feignOkHttpClientInterceptor;

    @Autowired(required = false)
    private FeignOkHttpUserInterceptor feignOkHttpUserInterceptor;

    private int feignOkHttpReadTimeout = 60;
    private int feignConnectTimeout = 60;
    private int feignWriteTimeout = 120;

    @Bean
    @ConditionalOnMissingBean(okhttp3.OkHttpClient.class)
    @Order(3)
    public okhttp3.OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().readTimeout(feignOkHttpReadTimeout, TimeUnit.SECONDS)
                .connectTimeout(feignConnectTimeout, TimeUnit.SECONDS)
                .writeTimeout(feignWriteTimeout, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool());
        if (feignOkHttpClientInterceptor == null && feignOkHttpUserInterceptor == null){
            logger.warn(String.format("feignOkHttpClientInterceptor和" +
                    "feignOkHttpUserInterceptor" +
                    "都未设置bean，请确认真的这么处理吗！！！"));
            return builder.build();
        }
        if (feignOkHttpClientInterceptor != null){
            builder.addInterceptor(feignOkHttpClientInterceptor);
        }
        if (feignOkHttpUserInterceptor != null){
            builder.addInterceptor(feignOkHttpUserInterceptor);
        }
        return builder.build();
    }
}
