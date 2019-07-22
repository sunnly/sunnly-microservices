package wang.sunnly.micro.services.scannable.security.auth.annotation;

import org.springframework.context.annotation.Import;
import wang.sunnly.micro.services.scannable.security.auth.config.SunnlyUserConfig;
import wang.sunnly.micro.services.scannable.security.auth.req.configuration.FeignOkHttpConfig;
import wang.sunnly.micro.services.scannable.security.auth.user.core.configuration.AuthReqUserWebConfiguration;
import wang.sunnly.micro.services.scannable.security.auth.user.core.runner.RefreshUserPubKeyRunner;
import wang.sunnly.micro.services.scannable.security.auth.user.core.schedule.RefreshUserPubKeySchedule;

import java.lang.annotation.*;

/**
 * SunnlyUserEnable
 *
 * @author Sunnly
 * @since 2019/7/6 0006 1:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({SunnlyUserConfig.class,
        RefreshUserPubKeySchedule.class,
        RefreshUserPubKeyRunner.class,
        AuthReqUserWebConfiguration.class,
        FeignOkHttpConfig.class})
public @interface EnableSunnlyUser {
}
