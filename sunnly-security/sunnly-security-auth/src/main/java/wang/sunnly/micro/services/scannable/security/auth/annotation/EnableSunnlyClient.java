package wang.sunnly.micro.services.scannable.security.auth.annotation;

import org.springframework.context.annotation.Import;
import wang.sunnly.micro.services.scannable.security.auth.config.SunnlyClientConfig;

import java.lang.annotation.*;

/**
 * SunnlyClientEnable
 *
 * @author Sunnly
 * @create 2019/7/6 0006 0:02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({SunnlyClientConfig.class})
@EnableSunnlyClientReq
@EnableSunnlyClientRes
public @interface EnableSunnlyClient {

}
