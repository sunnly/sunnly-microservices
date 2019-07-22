package wang.sunnly.micro.services.scannable.security.auth.annotation;

import java.lang.annotation.*;

/**
 * SunnlyClientEnable
 *
 * @author Sunnly
 * @since 2019/7/6 0006 0:02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableSunnlyClientReq
@EnableSunnlyClientRes
public @interface EnableSunnlyClient {

}
