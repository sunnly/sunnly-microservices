package wang.sunnly.micro.services.scannable.tools.logger.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import wang.sunnly.micro.services.scannable.tools.logger.aspect.SunnlyLoggerAspect;
import wang.sunnly.micro.services.scannable.tools.logger.service.LoggerService;
import wang.sunnly.micro.services.scannable.tools.logger.service.impl.LoggerServiceImpl;

/**
 * SunnlyLoggerConfig
 *
 * @author Sunnly
 * @since 2019/7/24 16:58
 */
@Configurable
public class SunnlyLoggerConfig {

    @Bean
    SunnlyLoggerAspect sunnlyLoggerAspect(){
        return new SunnlyLoggerAspect();
    }

    @Bean
    @ConditionalOnMissingBean(LoggerService.class)
    LoggerService loggerService(){
        return new LoggerServiceImpl();
    }
}
