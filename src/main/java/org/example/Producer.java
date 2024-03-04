package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.UUID;
import jakarta.jms.*;

import static jakarta.jms.Session.AUTO_ACKNOWLEDGE;

public class Producer {
    public static void main(String[] args) throws JMSException, InterruptedException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
        Destination test = session.createQueue("exampleQueueTwo");
        MessageProducer producer = session.createProducer(test);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        UUID uuid = UUID.randomUUID();
        for (int i = 0; i < 100; i++) {
            TextMessage testMessage = session.createTextMessage("Test message" + uuid);
            producer.send(testMessage);
            System.out.println("Sent!" + uuid);
        }
        Thread.sleep(1_000_000);
    }
}