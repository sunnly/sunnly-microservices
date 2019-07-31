package wang.sunnly.micro.services.scannable.demo.logger.service.impl;

import wang.sunnly.micro.services.scannable.tools.logger.entity.LoggerEntity;
import wang.sunnly.micro.services.scannable.tools.logger.service.LoggerService;

/**
 * MyLoggerService
 *
 * @author Sunnly
 * @since 2019/7/24 17:24
 */
public class MyLoggerServiceImpl implements LoggerService {
    @Override
    public void loggerHandler(LoggerEntity loggerEntity) {
        System.out.println("-----------------------------------");
        System.out.println(loggerEntity);
        System.out.println("-----------------------------------");
    }
}
