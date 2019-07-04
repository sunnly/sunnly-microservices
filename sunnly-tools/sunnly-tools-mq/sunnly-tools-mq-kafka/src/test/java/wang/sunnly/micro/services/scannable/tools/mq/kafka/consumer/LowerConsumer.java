package wang.sunnly.micro.services.scannable.tools.mq.kafka.consumer;

import kafka.api.FetchRequestBuilder;
import kafka.cluster.BrokerEndPoint;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.javaapi.message.MessageSet;
import kafka.message.MessageAndOffset;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LowerConsumer
 * 根据指定的topic Partition，offset来获取数据
 * @author Sunnly
 * @create 2019/7/4 0004 22:34
 */
public class LowerConsumer {
    public static void main(String[] args) {

        ArrayList<String[]> brokers = new ArrayList<>();
        brokers.add(new String[]{"192.168.101.13","9092"});
        brokers.add(new String[]{"192.168.101.13","9093"});
        brokers.add(new String[]{"192.168.101.13","9094"});


        String topic = "second";

        //分区
        int partitionId = 0;

        long offset = 2;

        LowerConsumer lowerConsumer = new LowerConsumer();
        lowerConsumer.getData(brokers, topic, partitionId, offset);

    }

    //找分区领导
    private BrokerEndPoint findLeader(List<String[]> brokers, String topic, int partitionId){

        for (String[] broker : brokers){
            SimpleConsumer getLeader = new SimpleConsumer(broker[0], Integer.parseInt(broker[1]),
                    1000, 1024 * 4, "getLeader");

            //创建一个主题元数据请求
            TopicMetadataRequest topicMetadataRequest = new TopicMetadataRequest(Collections.singletonList(topic));

            //获取主题元数据返回值
            TopicMetadataResponse topicMetadataResponse = getLeader.send(topicMetadataRequest);

            //解析元数据返回值
            List<TopicMetadata> topicMetadatas = topicMetadataResponse.topicsMetadata();

            for (TopicMetadata topicMetadata : topicMetadatas){
                //获取多个分区的元数据信息
                List<PartitionMetadata> partitionMetadatas = topicMetadata.partitionsMetadata();

                //遍历分区
                for (PartitionMetadata partitionMetadata : partitionMetadatas){
                    if (partitionId == partitionMetadata.partitionId()){
//                        partitionMetadata.replicas();
                        return partitionMetadata.leader();
                    }
                }
            }

        }


        return null;
    }

    //获取数据
    private void getData(List<String[]> brokers, String topic, int partitionId, long offset){
        BrokerEndPoint leader = findLeader(brokers, topic, partitionId);
        if (leader ==null){
            return;
        }


        //获取数据消费对象
        SimpleConsumer getData = new SimpleConsumer(leader.host(), leader.port(), 1000, 1024 * 4, "getData");

        //获取数据的对象
        kafka.api.FetchRequest fetchRequest = new FetchRequestBuilder().addFetch(topic, partitionId, offset, 50000).build();

        //获取数据返回值
        FetchResponse fetchResponse = getData.fetch(fetchRequest);

        //解析返回值
        ByteBufferMessageSet messageAndOffsets = fetchResponse.messageSet(topic, partitionId);

        for (MessageAndOffset messageAndOffset : messageAndOffsets){
            long offset1 = messageAndOffset.offset();
            ByteBuffer payload = messageAndOffset.message().payload();

            byte[] bytes = new byte[payload.limit()];
            payload.get(bytes);

            System.out.println(String.format("offset=%d bytes=%s",offset1, new String(bytes)));
        }

    }
}
