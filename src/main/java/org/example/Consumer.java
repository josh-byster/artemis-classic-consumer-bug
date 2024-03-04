package org.example;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import static jakarta.jms.Session.AUTO_ACKNOWLEDGE;

public class Consumer {
    public static void main(String[] args) throws JMSException, InterruptedException {
        // Change this to use org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory and it works as expected.
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61618");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
        Destination test = session.createQueue("exampleQueueTwo");
        MessageConsumer consumer = session.createConsumer(test);
        consumer.setMessageListener((message) -> {
            System.out.println(message);
        });

        Thread.sleep(1_000_000);
    }
}