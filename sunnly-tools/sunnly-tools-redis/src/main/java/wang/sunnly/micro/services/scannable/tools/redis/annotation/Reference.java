package wang.sunnly.micro.services.scannable.tools.redis.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Reference
 *
 * @author Sunnly
 * @create 2019/7/10 11:27
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Lazy
@Autowired
@Order
public @interface Reference {
    String value() default "";
}
