package wang.sunnly.micro.services.scannable.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * RabbitLoggerSource
 *
 * @author Sunnly
 * @since 2019/7/17 10:09
 */
public interface RabbitLoggerSource {

    String RABBIT_LOGGER_DEBUG = "rabbitLoggerDebug";
    String RABBIT_LOGGER_ERROR = "rabbitLoggerError";
    String RABBIT_TEST_DEBUG = "rabbitTestDebug";

    @Output(RABBIT_LOGGER_DEBUG)
    MessageChannel rabbitLoggerDebug();

    @Output(RABBIT_LOGGER_ERROR)
    MessageChannel rabbitLoggerError();

    @Output(RABBIT_TEST_DEBUG)
    MessageChannel rabbitTestDebug();

}
