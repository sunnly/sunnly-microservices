package wang.sunnly.micro.services.scannable.security.auth.client.req.configuration;

import com.alibaba.fastjson.JSONObject;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;
import wang.sunnly.micro.services.scannable.common.core.exception.BaseRuntimeException;

import java.io.IOException;

/**
 * FeignErrorDecoder
 *
 * @author Sunnly
 * @create 2019/6/22 0022 0:19
 */
@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            // 这里直接拿到微服务抛出的异常信息
            String message = Util.toString(response.body().asReader());
            JSONObject jmsg = JSONObject.parseObject(message);
            return new BaseRuntimeException(
                    jmsg.getString("message"),
                    jmsg.getInteger("status"));
        } catch (IOException ignored) {
        }
        return decode(methodKey, response);
    }

}
