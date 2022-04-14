package com.example.demo.entity;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ChatMessageTests {

    public ChatMessage chatMessage;
    public static final String USERNAME = "Test Username";
    public static final String MESSAGE = "Test Message";


    @BeforeEach
    void setUp(){
        chatMessage = new ChatMessage(USERNAME, MESSAGE);
    }

    @Test
    public void getUsernameTest() {
        Assert.assertEquals(USERNAME, chatMessage.getUsername());
    }

    @Test
    public void setUsernameTest() {
        //arrange
        String newUsername = "New Username";
        //act
        chatMessage.setUsername(newUsername);
        //assert
        Assert.assertEquals(newUsername, chatMessage.getUsername());
    }

    @Test
    public void getMessageTest() {
        Assert.assertEquals(MESSAGE, chatMessage.getMessage());
    }

    @Test
    public void setMessageTest() {
        //arrange
        String newMessage = "New Message";
        //act
        chatMessage.setMessage(newMessage);
        //assert
        Assert.assertEquals(newMessage, chatMessage.getMessage());
    }
}
