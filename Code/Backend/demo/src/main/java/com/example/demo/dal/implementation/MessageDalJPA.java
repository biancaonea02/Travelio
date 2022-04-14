package com.example.demo.dal.implementation;

import com.example.demo.dal.IMessageDal;
import com.example.demo.dao.MessageDao;
import com.example.demo.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageDalJPA implements IMessageDal {

    @Autowired
    MessageDao messageRepository;

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findById(id).get();
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessageByStatus(String status) {
        List<Message> messages = new ArrayList<>();
        for(Message message : messageRepository.findAll())
        {
            if(message.getStatus().equals(status))
            {
                messages.add(message);
            }
        }
        return messages;
    }

    @Override
    public void changeMessageStatus(Long id) {
        Message message = messageRepository.findById(id).get();
        message.setStatus("ANSWERED");
        messageRepository.save(message);
    }
}
