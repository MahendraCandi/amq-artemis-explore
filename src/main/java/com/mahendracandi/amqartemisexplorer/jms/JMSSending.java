package com.mahendracandi.amqartemisexplorer.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JMSSending {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("topicJmsTemplate")
    private JmsTemplate topicJmsTemplate;

    @Value("${queue.sending.name}")
    private String queueName;

    @Value("${topic.sending.name}")
    private String topicName;

    public void send(String parameter) {
        System.out.println("Sending to queue: " + parameter);
        jmsTemplate.convertAndSend(queueName, parameter);
    }

    public void sendToTopic(String parameter) {
        System.out.println("Sending to topic: " + parameter);
        topicJmsTemplate.convertAndSend(topicName, parameter);
    }
}
