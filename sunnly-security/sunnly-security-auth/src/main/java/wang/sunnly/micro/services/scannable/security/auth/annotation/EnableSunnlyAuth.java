package wang.sunnly.micro.services.scannable.security.auth.annotation;

import java.lang.annotation.*;

/**
 * SunnlyAuthEnable
 *
 * @author Sunnly
 * @create 2019/7/6 0006 1:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableSunnlyClient
@EnableSunnlyUser
public @interface EnableSunnlyAuth {
}
