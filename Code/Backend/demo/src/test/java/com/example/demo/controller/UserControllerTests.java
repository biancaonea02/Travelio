package com.example.demo.controller;


import com.example.demo.TestApplication;
import com.example.demo.entity.User;
import com.example.demo.enumeration.Role;
import com.example.demo.exception.domain.EmailExistsException;
import com.example.demo.exception.domain.UsernameExistsException;
import com.example.demo.service.UserService;
import com.example.demo.serviceInterfaces.IUserService;
import com.example.demo.utility.JWTTokenProvider;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.mail.MessagingException;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.test.context.support.WithMockUser;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = {UserController.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IUserService userService;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    JWTTokenProvider tokenProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser
    public void getUserByIdTest() throws Exception {
        //arrange
        User user= new User();
        user.setId(1L);
        Mockito.when(userService.getUserById(user.getId())).thenReturn(user);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/{id}",user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getUserByUsernameTest() throws Exception {
        //arrange
        User user= new User();
        user.setUsername("username");
        Mockito.when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/find/{username}",user.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getAllUsersTest() throws Exception {
        //arrange
        User user1 = new User(1L,"Username1","email@email.com2","test1234", "bla bla");
        User user2 = new User(2L,"Username1","email@email.com2","test1234", "bla bla");
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        Mockito.when(userService.getUsers()).thenReturn(users);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser
    public void addUserTest() throws Exception {
        //arrange
        User user = new User(1L, "USER_RANDOM_ID", "FIRST_NAME", "LAST_NAME", "USERNAME", "PASSWORD", "EMAIL", new Date(), new Date(), new Date(), Role.ROLE_USER.name(), Role.ROLE_USER.getAuthorities(), true, true);
        when(userService.addNewUser("a", "a", "a", "a", Role.ROLE_USER.name(), true, true)).thenReturn(user);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", user.getFirstName())
                        .param("lastName", user.getLastName())
                        .param("username", user.getUsername())
                        .param("email", user.getEmail())
                        .param("role", user.getRole())
                        .param("isNotLocked", user.getNotLocked().toString())
                        .param("isActive", user.getActive().toString())
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void updateUserTest() throws Exception {
        //arrange
        User user = new User(1L, "USER_RANDOM_ID", "FIRST_NAME", "LAST_NAME", "USERNAME", "PASSWORD", "EMAIL", new Date(), new Date(), new Date(), Role.ROLE_USER.name(), Role.ROLE_USER.getAuthorities(), true, true);
        when(userService.updateUserPersonalInformation(user.getUsername(), "a", "a")).thenReturn(user);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/updatePersonalInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("currentUsername", user.getUsername())
                        .param("username", "A")
                        .param("email", "B")
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());

    }

}
