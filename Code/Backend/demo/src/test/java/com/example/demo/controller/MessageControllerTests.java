package com.example.demo.controller;

import com.example.demo.entity.Message;
import com.example.demo.service.EmailService;
import com.example.demo.serviceInterfaces.IMessageService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = {MessageController.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(MessageController.class)
public class MessageControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IMessageService messageService;

    @MockBean
    EmailService emailService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser
    public void getMessageByIdTest() throws Exception {
        //arrange
        Message message = new Message();
        message.setId(1L);
        Mockito.when(messageService.getMessageById(message.getId())).thenReturn(message);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/message/{id}",message.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getAllMessagesTest() throws Exception {
        //arrange
        Message message1 = new Message(1L, "Name1", "Email1", "Content1", "PENDING");
        Message message2 = new Message(2L, "Name2", "Email2", "Content2", "PENDING");
        List<Message> messages = new ArrayList<>(Arrays.asList(message1, message2));
        Mockito.when(messageService.getAllMessages()).thenReturn(messages);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/message")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getNotAnsweredMessage() throws Exception {
        //arrange
        Message message1 = new Message(1L, "Name1", "Email1", "Content1", "NOT ANSWERED");
        Message message2 = new Message(2L, "Name2", "Email2", "Content2", "NOT ANSWERED");
        List<Message> messages = new ArrayList<>(Arrays.asList(message1, message2));
        Mockito.when(messageService.getMessageByStatus("NOT ANSWERED")).thenReturn(messages);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/message/notAnswered")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }
}
