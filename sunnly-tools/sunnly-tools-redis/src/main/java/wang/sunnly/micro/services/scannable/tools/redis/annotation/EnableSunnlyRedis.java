package wang.sunnly.micro.services.scannable.tools.redis.annotation;

import org.springframework.context.annotation.Import;
import wang.sunnly.micro.services.scannable.tools.redis.selector.SunnlyRedisConfigurationSelector;

import java.lang.annotation.*;

/**
 * EnableSunnlyRedis
 *
 * @author Sunnly
 * @create 2019/7/9 13:37
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SunnlyRedisConfigurationSelector.class})
@Documented
@Inherited
public @interface EnableSunnlyRedis {
    int value() default 0;
}
