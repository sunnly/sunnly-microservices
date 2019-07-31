package wang.sunnly.micro.services.scannable.tools.logger.handler;

import wang.sunnly.micro.services.scannable.tools.logger.entity.LoggerEntity;

/**
 * LoggerHandler
 *
 * @author Sunnly
 * @since 2019/7/25 9:45
 */
public interface LoggerHandler {

    void handler(String key, LoggerEntity entity);
}
