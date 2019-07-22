package wang.sunnly.micro.services.scannable.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import wang.sunnly.micro.services.scannable.source.KafkaLoggerSource;

/**
 * RabbitMessageProcessor
 *
 * @author Sunnly
 * @since 2019/7/17 10:15
 */
@EnableBinding(KafkaLoggerSource.class)
public class KafkaMessageProcessor {

    @Autowired
    private KafkaLoggerSource kafkaLoggerSource;

    public boolean debug(String message){
        return kafkaLoggerSource.kafkaLoggerDebug()
                .send(MessageBuilder.withPayload(message).build());
    }

    public boolean error(String message){
        return kafkaLoggerSource.kafkaLoggerError()
                .send(MessageBuilder.withPayload(message).build());
    }

    public boolean test(String message){
        return kafkaLoggerSource.kafkaTestDebug()
                .send(MessageBuilder.withPayload(message).build());
    }

//    @Bean
//    @InboundChannelAdapter(value = KafkaLoggerSource.KAFKA_LOGGER_DEBUG,
//            poller = @Poller(fixedDelay = "10", maxMessagesPerPoll = "1"))
////    @Transformer(inputChannel = KafkaLoggerSource.KAFKA_LOGGER_DEBUG,
////            outputChannel = KafkaLoggerSource.KAFKA_LOGGER_ERROR)
//
////    @ServiceActivator
//    public MessageSource<String> timerMessageSource() {
//        return () -> new GenericMessage<>("Hello Spring Cloud Stream");
//    }


}
