package org.example;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;

public class Consumer {
    public static void main(String[] args) throws JMSException, InterruptedException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
        Destination test = session.createQueue("test4");
        MessageConsumer consumer = session.createConsumer(test);
        consumer.setMessageListener((message) -> {
            System.out.println(message);
        });

        Thread.sleep(1_000_000);
    }
}