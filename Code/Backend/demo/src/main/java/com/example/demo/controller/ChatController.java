package com.example.demo.controller;


import com.example.demo.entity.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    public ChatMessage sendMessage(ChatMessage chatMessage) throws Exception {
        Thread.sleep(1000); // simulated delay
        return chatMessage;
    }
}
