package com.example.demo.entity;

import com.example.demo.enumeration.Role;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
public class ReservationTests {

    //reservation data
    public Reservation reservation;
    public static final Long RESERVATION_ID = 2L;
    public static final String CHECK_IN =  "2021-12-05 01:00:00.000000";
    public static final String CHECK_OUT = "2021-12-07 01:00:00.000000";
    public static final int NR_ADULTS = 2;
    public static final int NR_CHILDREN = 3;

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
    public static final String FACILITIES_HOTEL = "Facilities Test";

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
    private final User user = new User(USER_ID, USER_RANDOM_ID, FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, LAST_LOGIN_DATE, LAST_LOGIN_DATE_DISPLAY, JOIN_DATE, ROLE, AUTHORITIES, IS_ACTIVE, NOT_LOCKED);


    //room data
    public static final Long ROOM_ID = 1L;
    public static final int CAPACITY = 3;
    public static final double PRICE_PER_NIGHT = 100;
    public static final String TYPE = "single";
    public static final Boolean RESERVED = false;
    public static final int DIMENSION = 28;
    public static final String FACILITIES_ROOM = "Test Facilities";

    private final Hotel hotel = new Hotel(HOTEL_ID, NAME, CITY, COUNTRY, ADDRESS, RATING, PHONE_NR, FREE_PARKING, FREE_CANCELLATION,
            TOP_LOCATION, DESCRIPTION, FACILITIES_HOTEL, user);


    private final Room room = new Room(ROOM_ID, hotel, CAPACITY, PRICE_PER_NIGHT, TYPE, RESERVED, DIMENSION, FACILITIES_ROOM);


    @BeforeEach
    void setUp() throws ParseException {
        reservation = new Reservation(RESERVATION_ID, hotel, user,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").parse(CHECK_IN),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").parse(CHECK_OUT), NR_ADULTS, NR_CHILDREN, room);
    }


    @Test
    public void getIdTest()
    {
        Assert.assertEquals(RESERVATION_ID, reservation.getId());
    }

    @Test
    public void getHotelTest()
    {
        Assert.assertEquals(hotel, reservation.getHotelId());
    }

    @Test
    public void setHotelTest()
    {
        //arrange
        Hotel hotelToAssign = new Hotel();
        //act
        reservation.setHotelId(hotelToAssign);
        //assert
        Assert.assertEquals(hotelToAssign, reservation.getHotelId());
    }

    @Test
    public void getUserTest()
    {
        Assert.assertEquals(user, reservation.getUserId());
    }

    @Test
    public void setUserTest()
    {
        //arrange
        User userToAssign = new User();
        //act
        reservation.setUserId(userToAssign);
        //assert
        Assert.assertEquals(userToAssign, reservation.getUserId());
    }

    @Test
    public void getCheckInTest() throws ParseException {
      Date checkIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").parse(CHECK_IN);
      Assert.assertEquals(checkIn, reservation.getCheckIn());
    }

    @Test
    public void setCheckInTest() throws ParseException
    {
        //arrange
        Date newCheckIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").parse("2021-02-03 01:00:00.000000");
        //act
        reservation.setCheckIn(newCheckIn);
        //assert
        Assert.assertEquals(newCheckIn, reservation.getCheckIn());
    }

    @Test
    public void getCheckOutTest() throws ParseException {
        Date checkOut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").parse(CHECK_OUT);
        Assert.assertEquals(checkOut, reservation.getCheckOut());
    }

    @Test
    public void setCheckOutTest() throws ParseException
    {
        //arrange
        Date newCheckOut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").parse("2021-02-09 01:00:00.000000");
        //act
        reservation.setCheckOut(newCheckOut);
        //assert
        Assert.assertEquals(newCheckOut, reservation.getCheckOut());
    }

    @Test
    public void getNrAdultsTest()
    {
        Assert.assertEquals(NR_ADULTS, reservation.getNrAdults());
    }

    @Test
    public void setNrAdultsTest()
    {
        //arrange
        int newNrAdults = 5;
        //act
        reservation.setNrAdults(newNrAdults);
        //assert
        Assert.assertEquals(newNrAdults, reservation.getNrAdults());
    }

    @Test
    public void getNrChildrenTest()
    {
        Assert.assertEquals(NR_CHILDREN, reservation.getNrChildren());
    }

    @Test
    public void setNrChildrenTest()
    {
        //arrange
        int newNrChildren = 5;
        //act
        reservation.setNrChildren(newNrChildren);
        //assert
        Assert.assertEquals(newNrChildren, reservation.getNrChildren());
    }

    @Test
    public void getRoomTest()
    {
        Assert.assertEquals(room, reservation.getRoomId());
    }

    @Test
    public void setRoomTest()
    {
        //arrange
        Room roomToAssign = new Room();
        //act
        reservation.setRoomId(roomToAssign);
        //assert
        Assert.assertEquals(roomToAssign, reservation.getRoomId());
    }
}
