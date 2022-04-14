package com.example.demo.entity;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MessageTests {
    public Message message;
    public static final Long MESSAGE_ID = 32L;
    public static final String SENDER_NAME = "Test Sender Name";
    public static final String SENDER_EMAIL = "Test Sender Email";
    public static final String CONTENT = "Test Content";
    public static final String STATUS = "NOT ANSWERED";


    @BeforeEach
    void setUp(){
        message = new Message(MESSAGE_ID, SENDER_NAME, SENDER_EMAIL, CONTENT, STATUS);
    }


    @Test
    public void getIdTest()
    {
        Assert.assertEquals(MESSAGE_ID, message.getId());
    }

    @Test
    public void setIdTest()
    {
        //arrange
        Long newId = 2L;
        //act
        message.setId(newId);
        //assert
        Assert.assertEquals(newId, message.getId());
    }

    @Test
    public void getSenderNameTest() {
        Assert.assertEquals(SENDER_NAME, message.getSenderName());
    }

    @Test
    public void setSenderNameTest() {
        //arrange
        String newSenderName = "New Name";
        //act
        message.setSenderName(newSenderName);
        //assert
        Assert.assertEquals(newSenderName, message.getSenderName());
    }

    @Test
    public void getSenderEmailTest() {
        Assert.assertEquals(SENDER_EMAIL, message.getSenderEmail());
    }

    @Test
    public void setSenderEmailTest() {
        //arrange
        String newSenderEmail = "New Email";
        //act
        message.setSenderEmail(newSenderEmail);
        //assert
        Assert.assertEquals(newSenderEmail, message.getSenderEmail());
    }

    @Test
    public void getContentTest() {
        Assert.assertEquals(CONTENT, message.getContent());
    }

    @Test
    public void setContentTest() {
        //arrange
        String newContent = "New Content";
        //act
        message.setContent(newContent);
        //assert
        Assert.assertEquals(newContent, message.getContent());
    }
}
