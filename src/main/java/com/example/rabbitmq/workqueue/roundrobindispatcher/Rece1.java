package com.example.rabbitmq.workqueue.roundrobindispatcher;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Rece1 {
    private static final String QUEUE_NAME="simple-queue";
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("receive 1 msg:"+msg);
            }
        };
        boolean autoAck = true;
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);
    }
}
