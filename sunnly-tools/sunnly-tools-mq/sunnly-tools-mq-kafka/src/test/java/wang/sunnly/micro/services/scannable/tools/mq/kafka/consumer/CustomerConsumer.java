package wang.sunnly.micro.services.scannable.tools.mq.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

/**
 * CustomerConsumer
 *
 * @author Sunnly
 * @create 2019/7/4 0004 22:06
 */
public class CustomerConsumer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        //kafka集群
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.101.13:9092,192.168.101.13:9093,192.168.101.13:9094");
        //消费者组ID
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
        //是否自动提交offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        //自动提交的延迟
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //1.自动重置offset，一般消费后offset会被记录，
        // 那么如果消费他要么用低级api去实现，要么重新新建一个组，
        // 如果新建一个组必须重置offset，负责新组无法消费以前的数据
        //默认是最后的数字lastes，这里设置为earliest为最早的数字
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        //KV反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        //指定消费topic
//        kafkaConsumer.subscribe(Collections.singletonList("second"));
        kafkaConsumer.subscribe(Arrays.asList("second","first","third"));

        //2.这里是指定offset去消费，即重新消费的第二种写法
//        kafkaConsumer.assign(Collections.singletonList(new TopicPartition("second", 0)));
//        kafkaConsumer.seek(new TopicPartition("second", 0), 2);

        while (true) {
            //多久获取一次
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format("topic= %s, offset = %d, value = %s",record.topic(), record.offset(), record.value()));

            }
        }
    }
}
