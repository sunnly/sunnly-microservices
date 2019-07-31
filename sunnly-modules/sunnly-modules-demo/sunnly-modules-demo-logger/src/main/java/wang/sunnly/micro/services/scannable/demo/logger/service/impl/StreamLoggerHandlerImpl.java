package wang.sunnly.micro.services.scannable.demo.logger.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.tools.logger.entity.LoggerEntity;
import wang.sunnly.micro.services.scannable.tools.logger.handler.AbstractStreamLoggerHandler;

/**
 * StreamLoggerHandlerImpl
 *
 * @author Sunnly
 * @since 2019/7/25 10:05
 */
@Service
public class StreamLoggerHandlerImpl extends AbstractStreamLoggerHandler {
    @Override
    public void handler(String key, LoggerEntity entity) {
        System.out.println(key);
        System.out.println(entity);
    }
}
