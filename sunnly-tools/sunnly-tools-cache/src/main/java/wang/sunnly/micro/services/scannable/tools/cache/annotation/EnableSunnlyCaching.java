package wang.sunnly.micro.services.scannable.tools.cache.annotation;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import wang.sunnly.micro.services.scannable.tools.cache.enums.SunnlyCacheMode;
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
@EnableCaching
@Inherited
public @interface EnableSunnlyCaching {

    SunnlyCacheMode cacheMode() default SunnlyCacheMode.REDIS;

}
