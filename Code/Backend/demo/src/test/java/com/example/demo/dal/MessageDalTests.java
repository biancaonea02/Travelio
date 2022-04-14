package com.example.demo.dal;

import com.example.demo.dal.implementation.MessageDalJPA;
import com.example.demo.dao.MessageDao;
import com.example.demo.entity.Message;
import org.junit.Assert;
import org.junit.Before;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {MessageDalJPA.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class MessageDalTests {

    @MockBean
    MessageDao messageRepository;

    @InjectMocks
    MessageDalJPA messageDal;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getMessageByIdTest()
    {
        //arrange
        Optional<Message> message = Optional.of(new Message(1L, "Name", "Email", "Content", "PENDING"));
        //act
        when(messageRepository.findById(any(Long.class))).thenReturn(message);
        Message created = messageDal.getMessageById(message.get().getId());
        //assert
        Assert.assertEquals(Optional.of(created), message);
    }

    @Test
    public void getAllMessagesTest()
    {
        //arrange
        Message message1 = new Message(1L, "Name1", "Email1", "Content1", "PENDING");
        Message message2 = new Message(2L, "Name2", "Email2", "Content2", "PENDING");
        //act
        when(messageRepository.findAll()).thenReturn(Arrays.asList(message1, message2));
        List<Message> all = messageDal.getAllMessages();
        //assert
        Assert.assertEquals(all, Arrays.asList(message1, message2));
    }

    @Test
    public void addMessageTest()
    {
        //arrange
        Message message = new Message(1L, "Name", "Email", "Content", "PENDING");
        //act
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        Message created = messageDal.addMessage(message);
        //assert
        Assert.assertEquals(created, message);
    }
}
