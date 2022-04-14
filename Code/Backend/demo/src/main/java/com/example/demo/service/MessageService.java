package com.example.demo.service;


import com.example.demo.dal.IMessageDal;
import com.example.demo.entity.Message;
import com.example.demo.serviceInterfaces.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageService implements IMessageService {

    @Autowired
    private IMessageDal messageDal;

    public MessageService(IMessageDal messageDal)
    {
        this.messageDal = messageDal;
    }

    @Override
    public Message getMessageById(Long id) {
        return messageDal.getMessageById(id);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageDal.getAllMessages();
    }

    @Override
    public Message addMessage(Message message) {
        return messageDal.addMessage(message);
    }

    @Override
    public List<Message> getMessageByStatus(String status) {
        return messageDal.getMessageByStatus(status);
    }

    @Override
    public void changeMessageStatus(Long id) {
        messageDal.changeMessageStatus(id);
    }
}
