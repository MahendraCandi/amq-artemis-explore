package com.mahendracandi.amqartemisexplorer.controller;

import com.mahendracandi.amqartemisexplorer.jms.AMQ5Service;
import com.mahendracandi.amqartemisexplorer.jms.JMSSending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AppController {

    @Autowired
    JMSSending jmsSending;

//    @Autowired
//    AMQ5Service amq5Service;

    @GetMapping("/send")
    public String sendMessage(
            @RequestParam("message") String message,
            @RequestParam(name = "count", defaultValue = "1") Integer count) {
        for (int i = 0; i < count; i++) {
            jmsSending.send(String.format("%s %d", message, (i + 1)));
        }
        return "OK";
    }

    @GetMapping("/send-body")
    public String sendMessage(@RequestBody Map<String, Object> message) {
        jmsSending.send(message);
        return "OK";
    }

    @GetMapping("/send-body-amq5")
    public String sendMessageToAMQ5(@RequestBody Map<String, Object> message) {
        jmsSending.sendToQueueAmq5(message);
        return "OK";
    }

    @GetMapping("/send-topic")
    public String sendMessageToTopic(
            @RequestParam("message") String message,
            @RequestParam(name = "count", defaultValue = "1") Integer count) {
        for (int i = 0; i < count; i++) {
            jmsSending.sendToTopic(String.format("%s %d", message, (i + 1)));
        }
        return "OK";
    }
}
