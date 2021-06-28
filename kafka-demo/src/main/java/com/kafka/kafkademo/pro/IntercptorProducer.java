package com.kafka.kafkademo.pro;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class IntercptorProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        //kafka 集群，broker-list
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.62.129:9093");
        props.put("acks", "all");
        //重试次数
        props.put("retries", 1);
        //批次大小
        props.put("batch.size", 16384);
        //等待时间
        props.put("linger.ms", 1);
        //RecordAccumulator 缓冲区大小
        props.put("buffer.memory", 33554432);
        // 序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 配置拦截器,使用集合就可以添加多个拦截器
        List<String> inteList = new ArrayList<>();
        inteList.add("com.kafka.kafkademo.intercept.TimeIntercept");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, inteList);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

         //1、普通生产者
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("f-topic", "FJJA" + i));
        }

        // 如果没有调用close(),也可以调用sleep,有一个1ms的自动提交配置，休眠后，consumer也可以消费到数据
//        Thread.sleep(100);

        producer.close();
    }
}
