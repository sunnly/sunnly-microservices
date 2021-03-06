package wang.sunnly.micro.services.scannable.security.auth.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * IgnoreUserToken
 * 忽略用户鉴权
 * @author Sunnly
 * @Date 2019/6/12 20:15
 *
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD,ElementType.TYPE})
public @interface IgnoreUserToken {
}
