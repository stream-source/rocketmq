package org.apache.rocketmq.example.quickstart.demo.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;

/**
 * @author wx
 * @date 2020/10/31 9:58 下午
 */
public class SyncProducerDemo {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //设置生产者组
        DefaultMQProducer mqProducer = new DefaultMQProducer("syncProducer");
        //设置NameServer地址
        mqProducer.setNamesrvAddr("127.0.0.1:9876");
        //启动生产者
        mqProducer.start();

        for (int i = 0; i < 10; i ++) {
            //创建消息时，指定消息主题Topic，消息类型Tag,消息内容
            Message message = new Message("syncMessage","Tag1",("Hello word" + i).getBytes());
            //发送消息
            SendResult sendResult = mqProducer.send(message, 10000);
//            SendStatus status = sendResult.getSendStatus();
            System.out.println("消息发送结果:" + sendResult);
            TimeUnit.SECONDS.sleep(1);
        }
        //6.关闭生产者
        mqProducer.shutdown();
    }
}
