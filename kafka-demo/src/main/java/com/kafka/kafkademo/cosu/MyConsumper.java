package com.kafka.kafkademo.cosu;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MyConsumper {
    private static Map<TopicPartition, Long> currentOffset = new HashMap<>();
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.62.128:9092");
        // 设置消费者组
        props.put("group.id", "test");
        // 开启自动应答
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        // 反编译key、value
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 配置offset；如果想重新消费以前的数据，需要配置其为earliest，并且换一个消费者组（应为kafka里面会保存一个offset，未换组，其不会重头开始消费）；
        // 或者等kafka里面保存的offset失效，就是超过七天
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 消费者主题
        consumer.subscribe(Arrays.asList("f-topic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());

                // 同步、异步提交均需关闭自动提交配置，改为false；他们都会涉及到数据的重复消费、漏消
                //1、同步提交，当前线程会阻塞直到 offset 提交成功
//                consumer.commitSync();
            }

            // 2、异步提交
//            consumer.commitAsync(new OffsetCommitCallback() {
//                @Override
//                public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
//                    if (exception != null) {
//                        System.err.println("Commit failed for" + offsets);
//                    }
//                }
//            });
        }

        // 3、自定义提交offset，改方法适用于将数据存储在MySQL，设计表的时候，要考虑 主题-消费者-offset，不同的消费者有不同的offset；将其放到同一个事物，就可以回滚
        //消费者订阅主题
//        consumer.subscribe(Arrays.asList("first"), new ConsumerRebalanceListener() {
//            //该方法会在 Rebalance 之前调用
//            @Override
//            public void
//            onPartitionsRevoked(Collection<TopicPartition> partitions) {
//                commitOffset(currentOffset);
//            }
//            //该方法会在 Rebalance 之后调用
//            @Override
//            public void
//            onPartitionsAssigned(Collection<TopicPartition> partitions) {currentOffset.clear();
//                for (TopicPartition partition : partitions) {
//                    consumer.seek(partition, getOffset(partition));//定位到最近提交的 offset 位置继续消费
//                }
//            }
//        });
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(100);//消费者拉取数据
//            for (ConsumerRecord<String, String> record : records) {
//                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
//                currentOffset.put(new TopicPartition(record.topic(), record.partition()), record.offset());
//            }
//            commitOffset(currentOffset);//异步提交
//        }
//    }
//
//    //获取某分区的最新 offset
//    private static long getOffset(TopicPartition partition) {
//        return 0;
//    }
//    //提交该消费者所有分区的 offset
//    private static void commitOffset(Map<TopicPartition, Long> currentOffset) {
//    }
    }
}
