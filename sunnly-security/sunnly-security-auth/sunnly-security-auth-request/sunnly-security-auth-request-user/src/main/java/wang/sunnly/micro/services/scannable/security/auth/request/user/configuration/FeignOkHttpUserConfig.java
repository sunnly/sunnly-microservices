package wang.sunnly.micro.services.scannable.security.auth.request.user.configuration;

import feign.Feign;
import okhttp3.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wang.sunnly.micro.services.scannable.security.auth.request.user.interceptor.FeignOkHttpUserInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * @author Sunnly
 * @ClassName FeignOkHttpConfig
 * @Date 2019/6/17 0017 20:06
 **/

@AutoConfigureBefore(FeignAutoConfiguration.class)
@Configuration
@ConditionalOnClass(Feign.class)
public class FeignOkHttpUserConfig {

    @Autowired
    private FeignOkHttpUserInterceptor feignOkHttpUserInterceptor;

    private int feignOkHttpReadTimeout = 60;
    private int feignConnectTimeout = 60;
    private int feignWriteTimeout = 120;

    @Bean
    public okhttp3.OkHttpClient okHttpClient() {
        return new okhttp3.OkHttpClient.Builder().readTimeout(feignOkHttpReadTimeout, TimeUnit.SECONDS)
                .connectTimeout(feignConnectTimeout, TimeUnit.SECONDS)
                .writeTimeout(feignWriteTimeout, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool())
                .addInterceptor(feignOkHttpUserInterceptor)
                .build();
    }
}
