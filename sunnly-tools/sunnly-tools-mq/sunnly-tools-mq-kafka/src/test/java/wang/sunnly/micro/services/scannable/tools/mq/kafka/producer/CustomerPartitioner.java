package wang.sunnly.micro.services.scannable.tools.mq.kafka.producer;

import kafka.cluster.Partition;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * CustomerPartitioner
 * 自定义分区
 * @author Sunnly
 * @create 2019/7/4 0004 21:59
 */
public class CustomerPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        //都在0号分区，可以根据key等一些列hash
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
