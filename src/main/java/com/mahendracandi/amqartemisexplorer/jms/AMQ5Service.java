package com.mahendracandi.amqartemisexplorer.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Map;

/**
 * config and send become one class.
 * I prefer use src/main/java/com/mahendracandi/amqartemisexplorer/jms/ActiveMQClassicJMSConfig.java
 */
@Service
public class AMQ5Service {

    public static final String PROPERTY_NAME = "property_name";
    public static final String PROPERTY_VALUE = "property_value";

    @Value("${spring.activemq.broker-url}")
    private String urlBroker;

    @Value("${queue.amq5.sending.name}")
    private String queueName;

    @Autowired
    ObjectMapper objectMapper;

    public void send(Map<String, Object> requestBody) {
        try {
            String message = objectMapper.writeValueAsString(requestBody);
            send(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(urlBroker);

        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();

            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(queueName);

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            TextMessage textMessage = session.createTextMessage(message);
            textMessage.setStringProperty(PROPERTY_NAME, PROPERTY_VALUE);

            System.out.println("Send to AMQ 5: " + textMessage.getText());
            producer.send(textMessage);

            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
