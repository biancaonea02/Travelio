package com.example.demo.service;

import com.example.demo.dal.IMessageDal;
import com.example.demo.entity.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {MessageService.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class MessageServiceTests {

    @MockBean
    IMessageDal messageDal;

//    @InjectMocks
//    MessageService messageService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @Disabled
//    public void getMessageByIdTest()
//    {
//        //arrange
//        Message message = new Message(1L, "Name", "Email", "Content", "PENDING");
//        //act
//        when(messageDal.getMessageById(any(Long.class))).thenReturn(message);
//        Message created = messageService.getMessageById(message.getId());
//        //assert
//        Assert.assertEquals(created, message);
//    }
//
//    @Test
//    @Disabled
//    public void getAllMessagesTest()
//    {
//        //arrange
//        Message message1 = new Message(1L, "Name1", "Email1", "Content1", "PENDING");
//        Message message2 = new Message(2L, "Name2", "Email2", "Content2", "PENDING");
//        //act
//        when(messageDal.getAllMessages()).thenReturn(Arrays.asList(message1, message2));
//        List<Message> all = messageService.getAllMessages();
//        //assert
//        Assert.assertEquals(all, Arrays.asList(message1, message2));
//    }
//
//    @Test
//    @Disabled
//    public void addMessageTest()
//    {
//        //arrange
//        Message message = new Message(1L, "Name", "Email", "Content", "PENDING");
//        //act
//        when(messageDal.addMessage(any(Message.class))).thenReturn(message);
//        Message created = messageService.addMessage(message);
//        //assert
//        Assert.assertEquals(created, message);
//    }
}
