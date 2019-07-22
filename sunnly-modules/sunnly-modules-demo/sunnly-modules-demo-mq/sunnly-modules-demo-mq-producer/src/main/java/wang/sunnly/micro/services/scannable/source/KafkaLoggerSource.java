package wang.sunnly.micro.services.scannable.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * kafkaLoggerSource
 *
 * @author Sunnly
 * @since 2019/7/17 10:09
 */
public interface KafkaLoggerSource {

    String KAFKA_LOGGER_DEBUG = "kafkaLoggerDebug";
    String KAFKA_LOGGER_ERROR = "kafkaLoggerError";
    String KAFKA_TEST_DEBUG = "kafkaTestDebug";

    @Output(KAFKA_LOGGER_DEBUG)
    MessageChannel kafkaLoggerDebug();

    @Output(KAFKA_LOGGER_ERROR)
    MessageChannel kafkaLoggerError();

    @Output(KAFKA_TEST_DEBUG)
    MessageChannel kafkaTestDebug();

}
