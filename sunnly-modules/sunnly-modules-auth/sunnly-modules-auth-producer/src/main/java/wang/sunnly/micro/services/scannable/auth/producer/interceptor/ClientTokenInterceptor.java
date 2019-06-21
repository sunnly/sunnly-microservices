package wang.sunnly.micro.services.scannable.auth.producer.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import wang.sunnly.micro.services.scannable.auth.producer.service.AuthClientServices;
import wang.sunnly.micro.services.scannable.security.auth.core.properties.SecurityAuthClientProperties;

/**
 *
 * 封装Feign调用请求头,将服务鉴权信息封装到请求头
 * 此微服务中不引入security-client，故在这里进行封装
 * @author Sunnly
 * @ClassName ClientTokenInterceptor
 * @Date 2019/6/18 14:40
 * @Version 1.0
 */
public class ClientTokenInterceptor implements RequestInterceptor {

    @Autowired
    SecurityAuthClientProperties securityAuthClientProperties;

    @Autowired
    AuthClientServices authClientServices;
    @Override
    public void apply(RequestTemplate requestTemplate) {

        try {
            requestTemplate.header(securityAuthClientProperties.getTokenHeader(),
                    authClientServices.generateToken(securityAuthClientProperties.getId(),
                            securityAuthClientProperties.getSecret()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
