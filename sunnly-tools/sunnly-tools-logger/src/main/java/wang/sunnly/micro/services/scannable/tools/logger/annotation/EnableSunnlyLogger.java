package wang.sunnly.micro.services.scannable.tools.logger.annotation;

import org.springframework.context.annotation.Import;
import wang.sunnly.micro.services.scannable.tools.logger.configuration.SunnlyLoggerConfig;

import java.lang.annotation.*;

/**
 * EnableSunnlyLogger
 *
 * @author Sunnly
 * @since 2019/7/24 16:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({SunnlyLoggerConfig.class})
public @interface EnableSunnlyLogger {
}
