package wang.sunnly.micro.services.scannable.tools.verification.ali.plugs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wang.sunnly.micro.services.scannable.tools.verification.ali.plugs.service.impl.AliSmsValidateServiceImpl;
import wang.sunnly.micro.services.scannable.tools.verification.core.service.SmsValidateService;

/**
 * AliValidateCodeConfig
 *
 * @author Sunnly
 * @create 2019/9/1 0001 18:03
 */
@Configuration
public class AliValidateCodeConfig {

    @Bean
    public SmsValidateService smsValidateService(){
        return new AliSmsValidateServiceImpl();
    }
}
