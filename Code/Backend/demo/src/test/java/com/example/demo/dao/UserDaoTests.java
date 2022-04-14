package com.example.demo.dao;

import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class UserDaoTests {

    @Mock
    UserDao userRepository;


    @Test
    public void getUserByIdTest() {
        //arrange
        Long id = 1L;
        User user = new User(id, "First Name", "Last Name", "Username", "Email");
        //act
        when(userRepository.getById(any(Long.class))).thenReturn(user);
        //assert
        Assert.assertEquals(id, userRepository.getById(1L).getId());
    }

    @Test
    public void getAllTest() {
        //arrange
        User user1 = new User(1L, "First Name1", "Last Name1", "Username1", "Email1");
        User user2 = new User(2L, "First Name2", "Last Name2", "Username2", "Email2");
        //act
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        //assert
        Assert.assertEquals(Arrays.asList(user1, user2), userRepository.findAll());
    }

    @Test
    public void findUserByUsernameTest()
    {
        //arrange
        User user = new User(1L, "First Name", "Last Name", "Username", "Email");
        //act
        when(userRepository.findUserByUsername(any(String.class))).thenReturn(user);
        //assert
        Assert.assertEquals(user, userRepository.findUserByUsername("Username"));
    }

    @Test
    public void findUserByEmailTest()
    {
        //arrange
        User user = new User(1L, "First Name", "Last Name", "Username", "Email");
        //act
        when(userRepository.findUserByEmail(any(String.class))).thenReturn(user);
        //assert
        Assert.assertEquals(user, userRepository.findUserByEmail("Email"));
    }

    @Test
    public void addUserTest()
    {
        //arrange
        User user = new User(1L, "First Name", "Last Name", "Username", "Email");
        //act
        when(userRepository.save(any(User.class))).thenReturn(user);
        //assert
        Assert.assertEquals(user, userRepository.save(user));
    }
}

