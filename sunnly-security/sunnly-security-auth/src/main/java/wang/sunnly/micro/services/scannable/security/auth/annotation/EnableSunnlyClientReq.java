package wang.sunnly.micro.services.scannable.security.auth.annotation;

import org.springframework.context.annotation.Import;
import wang.sunnly.micro.services.scannable.security.auth.client.req.schedule.SecurityAuthClientSchedule;
import wang.sunnly.micro.services.scannable.security.auth.config.SunnlyClientReqConfig;
import wang.sunnly.micro.services.scannable.security.auth.req.configuration.FeignOkHttpConfig;

import java.lang.annotation.*;

/**
 * SunnlyClientReqEnable
 *
 * @author Sunnly
 * @since 2019/7/6 0006 1:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({SunnlyClientReqConfig.class,
        FeignOkHttpConfig.class,
        SecurityAuthClientSchedule.class})
public @interface EnableSunnlyClientReq {
}
