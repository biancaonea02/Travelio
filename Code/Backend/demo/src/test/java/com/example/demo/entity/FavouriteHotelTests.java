package com.example.demo.entity;

import com.example.demo.enumeration.Role;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FavouriteHotelTests {

    //favourite hotel data
    public FavouriteHotel favouriteHotel;
    public static final Long FAVOURITE_HOTEL_ID = 1L;

    //user data
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

    private final User user = new User(USER_ID, USER_RANDOM_ID, FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL,
            LAST_LOGIN_DATE, LAST_LOGIN_DATE_DISPLAY, JOIN_DATE, ROLE, AUTHORITIES, IS_ACTIVE, NOT_LOCKED);

    //hotel data
    public static final Long HOTEL_ID = 1L;
    public static final String NAME = "Name Test";
    public static final String CITY = "City Test";
    public static final String COUNTRY = "Country Test";
    public static final String ADDRESS = "Address Test";
    public static final int RATING = 4;
    public static final String PHONE_NR = "+40791208929";
    public static final Boolean FREE_PARKING = true;
    public static final Boolean FREE_CANCELLATION = true;
    public static final Boolean TOP_LOCATION = true;
    public static final String DESCRIPTION = "Description Test";
    public static final String FACILITIES = "Facilities Test";

    private final Hotel hotel = new Hotel(HOTEL_ID, NAME, CITY, COUNTRY, ADDRESS, RATING, PHONE_NR, FREE_PARKING, FREE_CANCELLATION,
            TOP_LOCATION, DESCRIPTION, FACILITIES, user);


    @BeforeEach
    void setUp(){
        favouriteHotel = new FavouriteHotel(FAVOURITE_HOTEL_ID, hotel, user);
    }



    @Test
    public void getIdTest()
    {
        Assert.assertEquals(FAVOURITE_HOTEL_ID, favouriteHotel.getId());
    }

    @Test
    public void setIdTest()
    {
        Long newId = 2L;
        favouriteHotel.setId(newId);
        Assert.assertEquals(newId, favouriteHotel.getId());
    }

    @Test
    public void getHotelTest()
    {
        Assert.assertEquals(hotel, favouriteHotel.getHotelId());
    }

    @Test
    @Transactional
    public void setHotelTest()
    {
        //arrange
        Hotel hotelToAssign = new Hotel();
        //act
        favouriteHotel.setHotelId(hotelToAssign);
        //assert
        Assert.assertEquals(hotelToAssign, favouriteHotel.getHotelId());
    }

    @Test
    @Transactional
    public void getUserTest()
    {
        Assert.assertEquals(user, favouriteHotel.getUserId());
    }

    @Test
    @Transactional
    public void setUserTest()
    {
        //arrange
        User userToAssign = new User();
        //act
        favouriteHotel.setUserId(userToAssign);
        //assert
        Assert.assertEquals(userToAssign, favouriteHotel.getUserId());
    }
}
