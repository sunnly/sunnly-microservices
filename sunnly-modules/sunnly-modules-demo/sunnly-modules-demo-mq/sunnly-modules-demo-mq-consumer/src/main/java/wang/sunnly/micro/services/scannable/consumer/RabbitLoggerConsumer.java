package wang.sunnly.micro.services.scannable.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import wang.sunnly.micro.services.scannable.sink.RabbitLoggerSink;

/**
 * RabbitLoggerConsumer
 *
 * @author Sunnly
 * @since 2019/7/17 0017 19:35
 */
@EnableBinding(RabbitLoggerSink.class)
public class RabbitLoggerConsumer {

    @StreamListener(target = RabbitLoggerSink.RABBIT_LOGGER_DEBUG
//            ,condition="headers['type']=='hello'"
//            , condition = "payload.class.simpleName=='Dog'"
        )
//    @SendTo(RabbitLoggerSink.RABBIT_LOGGER_ERROR)
    public void rabbitLoggerDebug(Message<String> message) {
        System.err.println("【RABBIT_LOGGER_DEBUG】：" + message.getPayload());
    }
    @StreamListener(RabbitLoggerSink.RABBIT_LOGGER_ERROR)
    public void rabbitLoggerError(Message<String> message) {
        System.err.println("【RABBIT_LOGGER_ERROR】：" + message.getPayload());
    }
    @StreamListener(RabbitLoggerSink.RABBIT_TEST_DEBUG)
    public void rabbitTestDebug(Message<String> message) {
        System.err.println("【RABBIT_TEST_DEBUG】：" + message.getPayload());
    }


}
