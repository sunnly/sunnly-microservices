package wang.sunnly.micro.services.scannable.tools.materials.annotation;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import wang.sunnly.micro.services.scannable.tools.materials.configuration.SunnlyUploadConfig;
import wang.sunnly.micro.services.scannable.tools.materials.properties.SunnlyUploadProperties;

import java.lang.annotation.*;

/**
 * EnableSunnlyUpload
 *
 * @author Sunnly
 * @since 2019/7/23 10:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({SunnlyUploadConfig.class})
@EnableConfigurationProperties(SunnlyUploadProperties.class)
public @interface EnableSunnlyUpload {
}
