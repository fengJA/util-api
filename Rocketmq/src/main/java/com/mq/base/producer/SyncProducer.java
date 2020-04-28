package com.mq.base.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 同步发送消息
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        // 创建生产者，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        // 制定NameServer的地址
        producer.setNamesrvAddr("192.168.4.27:9876");
        producer.setVipChannelEnabled(false);
        producer.start();

        for (int i = 0; i < 10; i++) {
            // 创建消息对象，消息主题，消息tag，消息体（消息内容）
            Message message = new Message("springboot-mq","tags1",("hellword" + i).getBytes());
            SendResult send = producer.send(message,10000);
            SendStatus status = send.getSendStatus();
            System.out.println("发送状态" + status);
            TimeUnit.SECONDS.sleep(1);
        }

        // 关闭生产者
        producer.shutdown();
    }
}
