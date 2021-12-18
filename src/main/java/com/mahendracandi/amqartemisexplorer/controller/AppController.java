package com.mahendracandi.amqartemisexplorer.controller;

import com.mahendracandi.amqartemisexplorer.jms.JMSSending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    JMSSending jmsSending;

    @GetMapping("/send")
    public String sendMessage(
            @RequestParam("message") String message,
            @RequestParam(name = "count", defaultValue = "1") Integer count) {
        for (int i = 0; i < count; i++) {
            jmsSending.send(String.format("%s %d", message, (i + 1)));
        }
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
