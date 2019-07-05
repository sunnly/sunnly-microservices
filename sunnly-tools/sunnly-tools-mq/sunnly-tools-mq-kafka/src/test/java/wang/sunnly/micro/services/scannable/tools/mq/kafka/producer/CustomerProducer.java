package wang.sunnly.micro.services.scannable.tools.mq.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * CustomerProducer
 *
 * @author Sunnly
 * @create 2019/7/4 0004 20:59
 */
public class CustomerProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        //kafka集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.sunnly.wang:9092,kafka.sunnly.wang:9093,kafka.sunnly.wang:9094");
        //应答级别
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        //重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 0);
        //批量大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        //提交延时
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        //缓存
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        //KV序列号类
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        //自定义分区
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "wang.sunnly.micro.services.scannable.tools.mq.kafka.producer.CustomerPartitioner");

        List<String> interceptors = Arrays.asList(
                "wang.sunnly.micro.services.scannable.tools.mq.kafka.interceptor.CountInterceptor",
                "wang.sunnly.micro.services.scannable.tools.mq.kafka.interceptor.TimeInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        Producer<String, String> producer = null;

        try {
            producer = new KafkaProducer<String, String>(properties);
            for (int i = 0; i < 100; i++) {
                String msg = "My Message " + i;
                Future<RecordMetadata> first = producer.send(new ProducerRecord<String, String>("second", msg)
                        ,
                        (recordMetadata, exception) -> {
                            if (exception != null){
                                System.out.println("发送失败");
                            }else {
                                System.out.println(recordMetadata.partition()+"======="+recordMetadata.offset());
                            }
                        }
            );
                System.out.println("Sent:" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }
    }
}
