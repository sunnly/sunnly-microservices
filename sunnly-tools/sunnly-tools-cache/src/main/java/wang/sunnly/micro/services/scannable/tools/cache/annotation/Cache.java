package wang.sunnly.micro.services.scannable.tools.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Cache
 *
 * @author Sunnly
 * @create 2019/7/7 0007 19:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Cache {
    String key() default "";
    int expire() default 3600;

}
