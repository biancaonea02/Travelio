package com.example.demo.entity;

import com.example.demo.enumeration.Role;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;

@RunWith(SpringRunner.class)
public class UserPrincipalTests {

    public UserPrincipal userPrincipal;
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
    private User user = new User(USER_ID, USER_RANDOM_ID, FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, LAST_LOGIN_DATE, LAST_LOGIN_DATE_DISPLAY, JOIN_DATE, ROLE, AUTHORITIES, IS_ACTIVE, NOT_LOCKED);

    @BeforeEach
    void setUp() {
        userPrincipal = new UserPrincipal(user);
    }


    @Test
    public void getAuthoritiesTest()
    {
        //assert
        Assert.assertEquals(new SimpleGrantedAuthority(user.getAuthorities()[0]), userPrincipal.getAuthorities().toArray()[0]);

    }

    @Test
    @Transactional
    public void getUsernameTest()
    {
        //assert
        Assert.assertEquals(user.getUsername(), userPrincipal.getUsername());
    }

    @Test
    @Transactional
    public void getPasswordTest()
    {
        //assert
        Assert.assertEquals(user.getPassword(), userPrincipal.getPassword());
    }

    @Test
    @Transactional
    public void getIsAccountNotLockedTest()
    {
        //assert
        Assert.assertEquals(user.getNotLocked(), userPrincipal.isAccountNonLocked());
    }

    @Test
    @Transactional
    public void getIsEnabledTest()
    {
        //assert
        Assert.assertEquals(user.getActive(), userPrincipal.isEnabled());
    }

}
