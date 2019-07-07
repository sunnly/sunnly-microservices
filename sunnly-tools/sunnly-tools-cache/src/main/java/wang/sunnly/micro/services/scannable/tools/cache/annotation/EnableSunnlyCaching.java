package wang.sunnly.micro.services.scannable.tools.cache.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import wang.sunnly.micro.services.scannable.tools.cache.enums.SunnlyAdviceMode;
import wang.sunnly.micro.services.scannable.tools.cache.selector.SunnlyCachingConfigurationSelector;

import java.lang.annotation.*;

/**
 * EnableSunnlyCaching
 *
 * @author Sunnly
 * @create 2019/7/7 0007 19:56
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SunnlyCachingConfigurationSelector.class})
@Documented
@Inherited
public @interface EnableSunnlyCaching {

    boolean proxyTargetClass() default false;

    SunnlyAdviceMode value() default SunnlyAdviceMode.REDIS;

    int order() default Ordered.LOWEST_PRECEDENCE;
}
