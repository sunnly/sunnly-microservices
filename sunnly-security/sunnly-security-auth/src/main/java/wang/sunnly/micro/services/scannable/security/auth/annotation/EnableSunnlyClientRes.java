package wang.sunnly.micro.services.scannable.security.auth.annotation;

import org.springframework.context.annotation.Import;
import wang.sunnly.micro.services.scannable.security.auth.client.reqs.schedule.RefreshAllowedClientSchedule;
import wang.sunnly.micro.services.scannable.security.auth.client.res.configuration.AuthClientResWebConfiguration;
import wang.sunnly.micro.services.scannable.security.auth.client.res.runner.RefreshClientPubKeyRunner;
import wang.sunnly.micro.services.scannable.security.auth.client.res.schedule.RefreshClientPubKeySchedule;
import wang.sunnly.micro.services.scannable.security.auth.config.SunnlyClientResConfig;

import java.lang.annotation.*;

/**
 * SunnlyClientResEnable
 *
 * @author Sunnly
 * @since 2019/7/6 0006 1:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({SunnlyClientResConfig.class,
        AuthClientResWebConfiguration.class,
        RefreshAllowedClientSchedule.class,
        RefreshClientPubKeyRunner.class,
        RefreshClientPubKeySchedule.class
})
public @interface EnableSunnlyClientRes {
}
