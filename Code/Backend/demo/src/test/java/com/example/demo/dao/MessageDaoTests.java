package com.example.demo.dao;

import com.example.demo.entity.Message;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class MessageDaoTests {

    @Mock
    MessageDao messageRepository;

    @Test
    public void getByIdTest() {
        //arrange
        Long id = 1L;
        Message message = new Message(id, "Name", "Email", "Content", "PENDING");
        //act
        when(messageRepository.getById(1L)).thenReturn(message);
        //assert
        Assert.assertEquals(id, messageRepository.getById(1L).getId());
    }

    @Test
    public void getAllMessagesTest() {
        //arrange
        Message message1 = new Message(1L, "Name1", "Email1", "Content1", "PENDING");
        Message message2 = new Message(2L, "Name2", "Email2", "Content2", "PENDING");
        //act
        when(messageRepository.findAll()).thenReturn(Arrays.asList(message1, message2));
        //assert
        Assert.assertEquals(Arrays.asList(message1, message2), messageRepository.findAll());
    }

    @Test
    public void addMessageTest() {
        //arrange
        Message message = new Message(1L, "Name", "Email", "Content", "PENDING");
        //act
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        //assert
        Assert.assertEquals(message, messageRepository.save(message));
    }
}
