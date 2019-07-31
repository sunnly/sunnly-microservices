package wang.sunnly.micro.services.scannable.tools.logger.annotation;

import java.lang.annotation.*;

/**
 * SunnlyLogger
 *
 * @author Sunnly
 * @since 2019/7/24 16:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SunnlyLogger {
    String value();
    String cata() default "logger";
}
