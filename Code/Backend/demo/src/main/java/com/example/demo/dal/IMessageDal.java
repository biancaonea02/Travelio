package com.example.demo.dal;

import com.example.demo.entity.Message;

import java.util.List;

public interface IMessageDal {
    Message getMessageById(Long id);
    List<Message> getAllMessages();
    Message addMessage(Message message);
    List<Message> getMessageByStatus(String status);
    void changeMessageStatus(Long id);
}
