package com.mahendracandi.amqartemisexplorer.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSListening {

    @JmsListener(destination = "${queue.listening.name}", containerFactory = "queueJmsListener")
    public void processQueue(String value) {
        System.out.println("Listening to a queue: " + value);
    }

    @JmsListener(destination = "${topic.listening.name}", containerFactory = "topicJmsListener")
    public void processTopic(String value) {
        System.out.println("Listening to a topic: " + value);
    }
}
