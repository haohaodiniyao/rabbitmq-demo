package com.example.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {
    public static void main(String[] args){
        System.out.println(new Date());
    }
    public static Connection getConnection(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("yaokai");
        connectionFactory.setPassword("yaokai");
        connectionFactory.setVirtualHost("/yaokai");
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
