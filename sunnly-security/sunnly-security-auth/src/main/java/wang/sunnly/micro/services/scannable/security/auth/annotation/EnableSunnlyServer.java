package wang.sunnly.micro.services.scannable.security.auth.annotation;

import org.springframework.context.annotation.Import;
import wang.sunnly.micro.services.scannable.security.auth.config.SunnlyServerConfig;

import java.lang.annotation.*;

/**
 * SunnlyServerEnable
 *
 * @author Sunnly
 * @since 2019/7/5 0005 23:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({SunnlyServerConfig.class})
public @interface EnableSunnlyServer {


}
