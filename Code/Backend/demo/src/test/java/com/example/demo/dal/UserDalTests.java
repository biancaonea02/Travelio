package com.example.demo.dal;

import com.example.demo.dal.implementation.UserDalJPA;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
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

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {UserDalJPA.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class UserDalTests {

    @MockBean
    UserDao userRepository;

    @InjectMocks
    UserDalJPA userDal;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveUserTest() {
        //arrange
        User user = new User(1L,"Username","email@email.com","test1234", "bla bla");
        //act
        when(userRepository.save(any(User.class))).thenReturn(user);
        User created = userDal.addUser(user);
        //assert
        Assert.assertEquals(created.getUsername(),user.getUsername());

    }

    @Test
    public void getByIdTest()
    {
        //arrange
        User user = new User(1L,"Username","email@email.com","test1234", "bla bla");
        //act
        when(userRepository.getById(any(Long.class))).thenReturn(user);
        User created = userDal.getUserById(user.getId());
        //assert
        Assert.assertEquals(created,user);
    }

    @Test
    public void getUserByUsernameTest()
    {
        //arrange
        User user = new User(1L,"Username","email@email.com","test1234", "bla bla");
        //act
        when(userRepository.findUserByUsername(any(String.class))).thenReturn(user);
        User created = userDal.getUserByUsername(user.getUsername());
        //assert
        Assert.assertEquals(created.getUsername(),user.getUsername());
    }

    @Test
    public void getUserByEmailTest()
    {
        //arrange
        User user = new User(1L,"Username","email@email.com","test1234", "bla bla");
        //act
        when(userRepository.findUserByEmail(any(String.class))).thenReturn(user);
        User created = userDal.getUserByEmail(user.getEmail());
        //assert
        Assert.assertEquals(created.getEmail(),user.getEmail());
    }

    @Test
    public void getAllUsersTest()
    {
        //arrange
        User user1 = new User(1L,"Username1","email@email.com2","test1234", "bla bla");
        User user2 = new User(2L,"Username1","email@email.com2","test1234", "bla bla");
        //act
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        List<User> allUsers = userDal.getAllUsers();
        //assert
        Assert.assertEquals(allUsers, Arrays.asList(user1, user2));
    }

    @Test
    public void getTheLastFiveUsersTest()
    {
        //arrange
        User user1 = new User(1L,"Username1","email@email.com2","test1234", "bla bla");
        User user2 = new User(2L,"Username2","email@email.com2","test1234", "bla bla");
        User user3 = new User(3L,"Username3","email@email.com2","test1234", "bla bla");
        User user4 = new User(4L,"Username4","email@email.com2","test1234", "bla bla");
        User user5 = new User(5L,"Username5","email@email.com2","test1234", "bla bla");
        //act
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2, user3, user4, user5));
        List<User> lastFiveUsers = userDal.getTheLastFiveUsers();
        //assert
        Assert.assertEquals(lastFiveUsers, Arrays.asList(user5, user4, user3, user2, user1));
    }
}
