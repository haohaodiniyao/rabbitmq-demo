package com.example.rabbitmq.workqueue.fairdispatcher;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Rece2 {
    private static final String QUEUE_NAME="simple_queue_durable";
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
                System.out.println("receive 1 msg:"+msg);
            }
        };
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);
    }
}
