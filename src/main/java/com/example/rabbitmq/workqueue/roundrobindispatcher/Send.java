package com.example.rabbitmq.workqueue.roundrobindispatcher;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
    private static final String QUEUE_NAME="simple-queue";
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for(int i=0;i<50;i++){
            String msg = "simple queue message "+i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes("UTF-8"));
        }
        channel.close();
        connection.close();
    }
}