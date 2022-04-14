package com.example.demo.service;

import com.example.demo.dal.IUserDal;
import com.example.demo.entity.User;
import com.example.demo.enumeration.Role;
import com.example.demo.exception.domain.EmailExistsException;
import com.example.demo.exception.domain.UsernameExistsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {UserService.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @MockBean
    IUserDal userDal;

//    @MockBean
//    LogInAttemptService logInAttemptService;
//
//    @MockBean
//    EmailService emailService;
//
//    @MockBean
//    BCryptPasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    UserService userService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @Disabled
//    public void registerTestSuccess() throws MessagingException, UsernameExistsException, EmailExistsException {
//        //arrange
//        User user = new User(1L,"Username","email@email.com","test1234", "bla bla");
//        //act
//        when(userDal.addUser(user)).thenReturn(user);
//        User created = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
//        //assert
//        Assert.assertEquals(created.getUsername(), user.getUsername());
//
//    }
//
//    @Test
//    @Disabled
//    public void getUserByIdTest()
//    {
//        //arrange
//        User user = new User(1L,"Username","email@email.com","test1234", "bla bla");
//        //act
//        when(userDal.getUserById(any(Long.class))).thenReturn(user);
//        User found = userService.getUserById(user.getId());
//        //assert
//        Assert.assertEquals(found.getUsername(), user.getUsername());
//    }
//
//    @Test
//    @Disabled
//    public void getUsersTest()
//    {
//        //arrange
//        User user1 = new User(1L,"Username1","email@email.com2","test1234", "bla bla");
//        User user2 = new User(2L,"Username1","email@email.com2","test1234", "bla bla");
//        //act
//        when(userDal.getAllUsers()).thenReturn(Arrays.asList(user1, user2));
//        List<User> allUsers = userService.getUsers();
//        //assert
//        Assert.assertEquals(allUsers, Arrays.asList(user1, user2));
//    }
//
//    @Test
//    @Disabled
//    public void getUserByUsernameTest()
//    {
//        //arrange
//        User user = new User(1L,"Username","email@email.com","test1234", "bla bla");
//        //act
//        when(userDal.getUserByUsername(any(String.class))).thenReturn(user);
//        User found = userService.getUserByUsername(user.getUsername());
//        //assert
//        Assert.assertEquals(found.getUsername(), user.getUsername());
//
//    }
//
//    @Test
//    @Disabled
//    public void getUserByEmailTest()
//    {
//        //arrange
//        User user = new User(1L,"Username","email@email.com","test1234", "bla bla");
//        //act
//        when(userDal.getUserByEmail(any(String.class))).thenReturn(user);
//        User found = userService.getUserByEmail(user.getEmail());
//        //assert
//        Assert.assertEquals(found.getEmail(), user.getEmail());
//    }
//
//    @Test
//    @Disabled
//    public void addNewUserTest() throws UsernameExistsException, EmailExistsException {
//        //arrange
//        User user = new User(1L, "USER_RANDOM_ID", "FIRST_NAME", "LAST_NAME", "USERNAME", "PASSWORD", "EMAIL", new Date(), new Date(), new Date(), Role.ROLE_USER.name(), Role.ROLE_USER.getAuthorities(), true, true);
//        //act
//        when(userService.addNewUser("any(String.class)", "any(String.class)", "any(String.class)", "any(String.class)", Role.ROLE_USER.name(), true, true)).thenReturn(user);
//        //assert
//        Assert.assertEquals(user.getUsername(), userService.addNewUser(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), Role.ROLE_USER.name(), false, false).getUsername());
//    }
}
