package com.mq.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 异步发送消息
 */
public class AsyncProducer {
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
            producer.send(message, new SendCallback() {
                // 发送成功的回调
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功了" + sendResult.getSendStatus() );
                }

                // 发送失败的回调
                public void onException(Throwable throwable) {
                    System.out.println("发送失败了" + throwable);
                }
            });

            TimeUnit.SECONDS.sleep(1);
        }

        // 关闭生产者
        producer.shutdown();
    }
}
