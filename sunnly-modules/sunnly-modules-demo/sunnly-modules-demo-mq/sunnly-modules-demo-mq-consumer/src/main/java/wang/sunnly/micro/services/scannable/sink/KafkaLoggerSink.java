package wang.sunnly.micro.services.scannable.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * kafkaLoggerSink
 *
 * @author Sunnly
 * @since 2019/7/17 0017 19:31
 */
public interface KafkaLoggerSink {

    String KAFKA_LOGGER_DEBUG = "kafkaLoggerDebug";
    String KAFKA_LOGGER_ERROR = "kafkaLoggerError";
    String KAFKA_TEST_DEBUG = "kafkaTestDebug";

    @Input(KAFKA_LOGGER_DEBUG)
    SubscribableChannel kafkaLoggerDebug();

    @Input(KAFKA_LOGGER_ERROR)
    SubscribableChannel kafkaLoggerError();

    @Input(KAFKA_TEST_DEBUG)
    SubscribableChannel kafkaTestDebug();
}
