package wang.sunnly.micro.services.scannable.tools.verification.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wang.sunnly.micro.services.scannable.tools.verification.core.service.ImageValidateService;
import wang.sunnly.micro.services.scannable.tools.verification.core.service.MailValidateService;
import wang.sunnly.micro.services.scannable.tools.verification.core.service.SmsValidateService;
import wang.sunnly.micro.services.scannable.tools.verification.core.storage.ValidateCodeStorage;
import wang.sunnly.micro.services.scannable.tools.verification.core.service.impl.ImageValidateServiceImpl;
import wang.sunnly.micro.services.scannable.tools.verification.core.service.impl.MailValidateServiceImpl;
import wang.sunnly.micro.services.scannable.tools.verification.core.service.impl.SmsValidateServiceImpl;
import wang.sunnly.micro.services.scannable.tools.verification.core.storage.impl.RedisStorageImpl;
import wang.sunnly.micro.services.scannable.tools.verification.core.storage.impl.SessionStrategyImpl;

/**
 * @author Sunnly
 * @ClassName ValidateCodeConfig
 * @Date 2019/6/16 0016 0:12
 **/
@Configuration
public class ValidateCodeConfig {

    @Bean
    @ConditionalOnMissingBean(ImageValidateService.class)
    public ImageValidateService imageValidateService() {
        return new ImageValidateServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(MailValidateService.class)
    public MailValidateService mailValidateService() {
        return new MailValidateServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(SmsValidateService.class)
    public SmsValidateService smsValidateService() {
        return new SmsValidateServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(ValidateCodeStorage.class)
    public ValidateCodeStorage validateCodeStorage() {
//        return new SessionStrategyImpl();
        return new RedisStorageImpl();
    }

}
