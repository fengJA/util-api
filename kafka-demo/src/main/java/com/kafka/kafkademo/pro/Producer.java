package com.kafka.kafkademo.pro;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Producer {
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
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // 1、普通生产者
//        for (int i = 0; i < 10; i++) {
//            producer.send(new ProducerRecord<>("f-topic", "FJJA" + i));
//        }

        // 2、有回调函数
//        for (int i = 0; i < 10; i++) {
//            producer.send(new ProducerRecord<>("f-topic", "FJJA" + i), new Callback() {
//                @Override
//                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//                    if (e == null){
//                        // offset是从0开始
//                        System.out.println(recordMetadata.partition() + "==" + recordMetadata.offset());
//                    }else {
//                        System.out.println(e.getMessage());
//                    }
//                }
//            });
//        }

        // 3、分区
        props.put("partitioner.class", "com.kafka.kafkademo.par.MyPartition");
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("f-topic", i%3,"123","IAMFJJA" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null){
                        // offset是从0开始
                        System.out.println(recordMetadata.partition() + "==" + recordMetadata.offset());
                    }else {
                        System.out.println(e.getMessage());
                    }
                }
            });
        }

        // 同步发送的意思就是，一条消息发送之后，会阻塞当前线程，直至返回 ack。
        //由于 send 方法返回的是一个 Future 对象，根据 Futrue 对象的特点，我们也可以实现同
        //步发送的效果，只需在调用 Future 对象的 get 方发即可
//        for (int i = 0; i < 10; i++) {
//            Future<RecordMetadata> send = producer.send(new ProducerRecord<>("f-topic", "FJJA" + i));
//            try {
//                send.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }

        producer.close();
    }
}
