package com.example.demo.entity;

import com.example.demo.constant.Authorities;
import com.example.demo.enumeration.Role;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;

@RunWith(SpringRunner.class)
public class UserTests {

    public User user;
    public static final Long USER_ID = 1L;
    public static final String USER_RANDOM_ID = "Random Id Test";
    public static final String FIRST_NAME = "FirstName Test";
    public static final String LAST_NAME = "LastName Test";
    public static final String USERNAME = "Username Test";
    public static final String PASSWORD = "Password Test";
    public static final String EMAIL = "unittest@gmail.com";
    public static final String ROLE = "ROLE_USER";
    public static final String[] AUTHORITIES = Role.valueOf(ROLE).getAuthorities();
    public static final Boolean IS_ACTIVE = true;
    public static final Boolean NOT_LOCKED = true;
    public static final Date JOIN_DATE = new Date();
    public static final Date LAST_LOGIN_DATE = new Date();
    public static final Date LAST_LOGIN_DATE_DISPLAY = new Date();

    @BeforeEach
    void setUp() {
        user = new User(USER_ID, USER_RANDOM_ID, FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, LAST_LOGIN_DATE, LAST_LOGIN_DATE_DISPLAY, JOIN_DATE, ROLE, AUTHORITIES, IS_ACTIVE, NOT_LOCKED);
    }

    @Test
    public void getIdTest()
    {
        Assert.assertEquals(USER_ID, user.getId());
    }

    @Test
    public void setIdTest()
    {
        //arrange
        Long newId = 2L;
        //act
        user.setId(newId);
        //assert
        Assert.assertEquals(newId, user.getId());

    }

    @Test
    public void getFirstNameTest()
    {
        Assert.assertEquals(FIRST_NAME, user.getFirstName());
    }

    @Test
    public void setFirstNameTest() {
        //arrange
        String newFirstName = "New First Name";
        //act
        user.setFirstName(newFirstName);
        //assert
        Assert.assertEquals(newFirstName, user.getFirstName());
    }

    @Test
    public void getLastNameTest()
    {
        Assert.assertEquals(LAST_NAME, user.getLastName());
    }

    @Test
    public void setLastNameTest() {
        //arrange
        String newLastName = "New Last Name";
        //act
        user.setLastName(newLastName);
        //assert
        Assert.assertEquals(newLastName, user.getLastName());
    }

    @Test
    public void getUsernameTest()
    {
        Assert.assertEquals(USERNAME, user.getUsername());
    }

    @Test
    public void setUsernameTest()
    {
        //arrange
        String newUsername = "New Username";
        //act
        user.setUsername(newUsername);
        //assert
        Assert.assertEquals(newUsername, user.getUsername());
    }

    @Test
    public void getEmailTest()
    {
        Assert.assertEquals(EMAIL, user.getEmail());
    }

    @Test
    public void setEmailTest()
    {
        //arrange
        String newEmail = "New Email";
        //act
        user.setEmail(newEmail);
        //assert
        Assert.assertEquals(newEmail, user.getEmail());
    }

    @Test
    public void getRoleTest()
    {
        Assert.assertEquals(ROLE, user.getRole());
    }

    @Test
    public void setRoleTest()
    {
        //arrange
        String newRole= "ROLE_USER";
        //act
        user.setRole(newRole);
        //assert
        Assert.assertEquals(newRole, user.getRole());
    }

    @Test
    public void getAuthoritiesTest()
    {
        Assertions.assertEquals(AUTHORITIES[0], user.getAuthorities()[0]);
    }

    @Test
    public void setAuthoritiesTest()
    {
        //arrange
        String[] newAuthorities = Authorities.USER_AUTHORITIES;
        //act
        user.setAuthorities(Role.valueOf("ROLE_ADMIN").getAuthorities());
        //assert
        Assertions.assertEquals(newAuthorities[0], user.getAuthorities()[0]);
    }

    @Test
    public void getIsActiveTest()
    {
        Assert.assertEquals(IS_ACTIVE, user.getActive());
    }

    @Test
    public void setIsActiveTest()
    {
        //arrange

        //act
        user.setActive(false);
        //assert
        Assert.assertEquals(false, user.getActive());
    }

    @Test
    public void getIsNotLockedTest()
    {
        Assert.assertEquals(NOT_LOCKED, user.getNotLocked());
    }

    @Test
    public void setIsNotLockedTest()
    {
        //arrange

        //act
        user.setNotLocked(false);
        //assert
        Assert.assertEquals(false, user.getNotLocked());
    }

}
