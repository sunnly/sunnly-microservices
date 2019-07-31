package wang.sunnly.micro.services.scannable.tools.fastdfs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.sunnly.micro.services.scannable.processor.KafkaMessageProcessor;
import wang.sunnly.micro.services.scannable.tools.fastdfs.service.KafKaLoggerService;

/**
 * RabbitLoggerServiceImpl
 *
 * @author Sunnly
 * @since 2019/7/17 12:04
 */
@Service
public class KafkaLoggerServiceImpl implements KafKaLoggerService {


//    @Qualifier()
    @Autowired
    KafkaMessageProcessor kafkaMessageProcessor;

    @Override
    public boolean debug(String message) {
        return kafkaMessageProcessor.debug(message);
    }

    @Override
    public boolean error(String message) {
        return kafkaMessageProcessor.error(message);
    }

    @Override
    public boolean test(String message) {
        return kafkaMessageProcessor.test(message);
    }
}
