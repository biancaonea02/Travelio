package com.example.demo.controller;


import com.example.demo.entity.Message;
import com.example.demo.service.EmailService;
import com.example.demo.serviceInterfaces.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @Autowired
    private EmailService emailService;

    public MessageController(IMessageService iMessageService, EmailService emailService)
    {
        this.iMessageService = iMessageService;
        this.emailService = emailService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") Long id)
    {
        Message messageById = iMessageService.getMessageById(id);
        return new ResponseEntity<>(messageById, OK);
    }

    @GetMapping()
    public ResponseEntity<List<Message>> getAllMessages()
    {
        List<Message> allMessages = iMessageService.getAllMessages();
        return new ResponseEntity<>(allMessages, OK);
    }

    @PostMapping("/addMessage")
    public ResponseEntity<Message> addMessage(@RequestBody Message message)
    {
        iMessageService.addMessage(message);
        return new ResponseEntity<>(message, OK);
    }

    @GetMapping("/notAnswered")
    public ResponseEntity<List<Message>> getUnreadMessages()
    {
        return new ResponseEntity<>(iMessageService.getMessageByStatus("NOT ANSWERED"), OK);
    }

    @PostMapping("/sendMessageResponse")
    public void sendMessageResponse(@PathParam("id") Long id,
                                    @PathParam("name") String name,
                                    @PathParam("subject") String subject,
                                    @PathParam("content") String content,
                                    @PathParam("email") String email) throws MessagingException {
        iMessageService.changeMessageStatus(id);
        emailService.sendMessageResponse(name, subject, content, email);
    }

}
