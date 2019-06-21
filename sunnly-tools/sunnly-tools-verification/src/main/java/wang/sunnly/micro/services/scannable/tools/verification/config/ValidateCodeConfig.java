package wang.sunnly.micro.services.scannable.tools.verification.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wang.sunnly.micro.services.scannable.tools.verification.service.ImageValidateService;
import wang.sunnly.micro.services.scannable.tools.verification.service.MailValidateService;
import wang.sunnly.micro.services.scannable.tools.verification.service.SmsValidateService;
import wang.sunnly.micro.services.scannable.tools.verification.service.ValidateCodeStorage;
import wang.sunnly.micro.services.scannable.tools.verification.service.impl.ImageValidateServiceImpl;
import wang.sunnly.micro.services.scannable.tools.verification.service.impl.MailValidateServiceImpl;
import wang.sunnly.micro.services.scannable.tools.verification.service.impl.SmsValidateServiceImpl;
import wang.sunnly.micro.services.scannable.tools.verification.service.impl.ValidateCodeStorageImpl;

/**
 * @author Sunnly
 * @ClassName ValidateCodeConfig
 * @Date 2019/6/16 0016 0:12
 **/
@Configuration
public class ValidateCodeConfig {

    @Bean
    @ConditionalOnMissingBean(ImageValidateService.class)
    public ImageValidateService imageValidateService(){
        return new ImageValidateServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(MailValidateService.class)
    public MailValidateService mailValidateService(){
        return new MailValidateServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(SmsValidateService.class)
    public SmsValidateService smsValidateService(){
        return new SmsValidateServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(ValidateCodeStorage.class)
    public ValidateCodeStorage validateCodeStorage(){
        return new ValidateCodeStorageImpl();
    }

}
