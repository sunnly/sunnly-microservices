package wang.sunnly.micro.services.scannable.security.auth.client.req.configuration;

import feign.Feign;
import okhttp3.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import wang.sunnly.micro.services.scannable.security.auth.client.req.interceptor.FeignOkHttpClientInterceptor;

import java.util.concurrent.TimeUnit;

/**
 *
 * FeignOkHttpClientConfig
 * @author Sunnly
 * @create 2019/6/21 16:05
 */

@AutoConfigureBefore(FeignAutoConfiguration.class)
@Configuration
@ConditionalOnClass(Feign.class)
public class FeignOkHttpClientConfig {

    @Autowired
    private FeignOkHttpClientInterceptor feignOkHttpClientInterceptor;

    private int feignOkHttpReadTimeout = 60;
    private int feignConnectTimeout = 60;
    private int feignWriteTimeout = 120;

    @Bean
    @ConditionalOnMissingBean(okhttp3.OkHttpClient.class)
    @Order(5)
    public okhttp3.OkHttpClient okHttpClient() {
        return new okhttp3.OkHttpClient.Builder().readTimeout(feignOkHttpReadTimeout, TimeUnit.SECONDS)
                .connectTimeout(feignConnectTimeout, TimeUnit.SECONDS)
                .writeTimeout(feignWriteTimeout, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool())
                .addInterceptor(feignOkHttpClientInterceptor)
                .build();
    }
}
