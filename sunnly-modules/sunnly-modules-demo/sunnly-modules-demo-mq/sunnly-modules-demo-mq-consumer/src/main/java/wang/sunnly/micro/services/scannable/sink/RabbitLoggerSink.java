package wang.sunnly.micro.services.scannable.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * RabbitLoggerSink
 *
 * @author Sunnly
 * @since 2019/7/17 0017 19:31
 */
public interface RabbitLoggerSink {

    String RABBIT_LOGGER_DEBUG = "rabbitLoggerDebug";
    String RABBIT_LOGGER_ERROR = "rabbitLoggerError";
    String RABBIT_TEST_DEBUG = "rabbitTestDebug";

    @Input(RABBIT_LOGGER_DEBUG)
    SubscribableChannel rabbitLoggerDebug();

    @Input(RABBIT_LOGGER_ERROR)
    SubscribableChannel rabbitLoggerError();

    @Input(RABBIT_TEST_DEBUG)
    SubscribableChannel rabbitTestDebug();
}
