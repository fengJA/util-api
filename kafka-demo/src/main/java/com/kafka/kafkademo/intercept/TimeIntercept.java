package com.kafka.kafkademo.intercept;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class TimeIntercept implements ProducerInterceptor<String,String> {
    private int susNum;
    private int errNum;

    @Override
    public void configure(Map<String, ?> map) {

    }

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        // 针对事件拦截器
        return new ProducerRecord<>(record.topic(), record.partition(), record.timestamp(),
                record.key(), System.currentTimeMillis() + "," + record.value());
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        // 针对数量统计拦截器
        if (null == recordMetadata){
            susNum ++;
        }else {
            errNum ++;
        }
    }

    @Override
    public void close() {
        System.out.println("sucess: " + susNum);
        System.out.println("error: " + errNum);
    }
}
