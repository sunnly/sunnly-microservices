package wang.sunnly.micro.services.scannable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.processor.RabbitMessageProcessor;
import wang.sunnly.micro.services.scannable.service.RabbitLoggerService;

/**
 * RabbitLoggerServiceImpl
 *
 * @author Sunnly
 * @create 2019/7/17 12:04
 */
@Service
public class RabbitLoggerServiceImpl implements RabbitLoggerService {

    @Autowired
    RabbitMessageProcessor rabbitMessageProcessor;

    @Override
    public boolean debug(String message) {
        return rabbitMessageProcessor.debug(message);
    }

    @Override
    public boolean error(String message) {
        return rabbitMessageProcessor.error(message);
    }

    @Override
    public boolean test(String message) {
        return rabbitMessageProcessor.test(message);
    }
}
