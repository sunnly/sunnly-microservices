package wang.sunnly.micro.services.scannable.tools.mq.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * CountInterceptor
 *
 * @author Sunnly
 * @create 2019/7/4 0004 23:51
 */
public class CountInterceptor implements ProducerInterceptor<String,String> {

    private int successCount = 0;
    private int errorCount = 0;
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null){
            successCount++;
        }else {
            errorCount ++;
        }
    }

    @Override
    public void close() {
        System.out.println(String.format("发送成功%s,发送失败%s",successCount,errorCount));
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
