package wang.sunnly.micro.services.scannable.tools.logger.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wang.sunnly.micro.services.scannable.tools.logger.entity.LoggerEntity;

/**
 * LoggerService
 *
 * @author Sunnly
 * @since 2019/7/24 17:02
 */
public interface LoggerService {
    void loggerHandler(LoggerEntity loggerEntity);
}
