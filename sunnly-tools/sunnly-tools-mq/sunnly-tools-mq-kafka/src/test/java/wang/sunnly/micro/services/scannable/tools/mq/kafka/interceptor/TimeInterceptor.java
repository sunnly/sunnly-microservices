package wang.sunnly.micro.services.scannable.tools.mq.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * TimeInterceptor
 *
 * @author Sunnly
 * @create 2019/7/4 0004 23:48
 */
public class TimeInterceptor implements ProducerInterceptor<String,String> {

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return new ProducerRecord(producerRecord.topic(),producerRecord.key(),
        String.format("%s %s", df.format(new Date()),
        producerRecord.value()));
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }



}
