package com.example.demo.entity;

import com.example.demo.enumeration.Role;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
public class HotelApplicationTests {

    //hotel application data
    public HotelApplication hotelApplication;
    public static final Long HOTEL_APPLICATION_ID = 1L;
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
    public static final String STATUS = "PENDING";

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

    @BeforeEach
    void setUp(){
        hotelApplication = new HotelApplication(HOTEL_APPLICATION_ID, NAME, CITY, COUNTRY, ADDRESS,
                RATING, PHONE_NR, FREE_PARKING, FREE_CANCELLATION, TOP_LOCATION, DESCRIPTION, FACILITIES, user, STATUS);
    }

    @Test
    public void getByIdTest()
    {
        Assert.assertEquals(HOTEL_APPLICATION_ID, hotelApplication.getId());
    }

    @Test
    public void setIdTest()
    {
        //arrange
        Long newId = 2L;
        //act
        hotelApplication.setId(newId);
        //assert
        Assert.assertEquals(newId, hotelApplication.getId());
    }

    @Test
    public void getNameTest()
    {
        Assert.assertEquals(NAME, hotelApplication.getName());
    }

    @Test
    public void setNameTest()
    {
        //arrange
        String newName = "New Name";
        //act
        hotelApplication.setName(newName);
        //assert
        Assert.assertEquals(newName, hotelApplication.getName());
    }

    @Test
    public void getCityTest()
    {
        Assert.assertEquals(CITY, hotelApplication.getCity());
    }

    @Test
    public void setCityTest()
    {
        //arrange
        String newCity = "New City";
        //act
        hotelApplication.setCity(newCity);
        //assert
        Assert.assertEquals(newCity, hotelApplication.getCity());
    }

    @Test
    public void getCountryTest()
    {
        Assert.assertEquals(COUNTRY, hotelApplication.getCountry());
    }

    @Test
    public void setCountryTest()
    {
        //arrange
        String newCountry = "New Country";
        //act
        hotelApplication.setCountry(newCountry);
        //assert
        Assert.assertEquals(newCountry, hotelApplication.getCountry());
    }

    @Test
    public void getAddressTest()
    {
        Assert.assertEquals(ADDRESS, hotelApplication.getAddress());
    }

    @Test
    public void setAddressTest()
    {
        //arrange
        String newAddress = "New Address";
        //act
        hotelApplication.setAddress(newAddress);
        //assert
        Assert.assertEquals(newAddress, hotelApplication.getAddress());
    }

    @Test
    public void getRatingTest()
    {
        Assert.assertEquals(RATING, hotelApplication.getRating());
    }

    @Test
    public void setRatingTest()
    {
        //arrange
        int newRating = 5;
        //act
        hotelApplication.setRating(newRating);
        //assert
        Assert.assertEquals(newRating, hotelApplication.getRating());
    }

    @Test
    public void getPhoneNumberTest()
    {
        Assert.assertEquals(PHONE_NR, hotelApplication.getPhoneNumber());
    }

    @Test
    public void setPhoneNumberTest()
    {
        //arrange
        String newPhoneNr = "0727412184";
        //act
        hotelApplication.setPhoneNumber(newPhoneNr);
        //assert
        Assert.assertEquals(newPhoneNr, hotelApplication.getPhoneNumber());
    }

    @Test
    public void getFreeParkingTest()
    {
        Assert.assertEquals(FREE_PARKING, hotelApplication.getFreeParking());
    }

    @Test
    public void setFreeParkingTest()
    {
        //arrange

        //act
        hotelApplication.setFreeParking(true);
        //assert
        Assert.assertEquals(true, hotelApplication.getFreeParking());
    }

    @Test
    public void getFreeCancellationTest()
    {
        Assert.assertEquals(FREE_CANCELLATION, hotelApplication.getFreeCancellation());
    }

    @Test
    public void setFreeCancellationTest()
    {
        //arrange

        //act
        hotelApplication.setFreeCancellation(true);
        //assert
        Assert.assertEquals(true, hotelApplication.getFreeCancellation());
    }

    @Test
    public void getTopLocationTest()
    {
        Assert.assertEquals(TOP_LOCATION, hotelApplication.getTopLocation());
    }

    @Test
    public void setTopLocationTest()
    {
        //arrange

        //act
        hotelApplication.setTopLocation(true);
        //assert
        Assert.assertEquals(true, hotelApplication.getTopLocation());
    }

    @Test
    public void getDescriptionTest()
    {
        Assert.assertEquals(DESCRIPTION, hotelApplication.getDescription());
    }

    @Test
    public void setDescriptionTest()
    {
        //arrange
        String newDescription = "New Description";
        //act
        hotelApplication.setDescription(newDescription);
        //assert
        Assert.assertEquals(newDescription, hotelApplication.getDescription());
    }

    @Test
    public void getFacilitiesTest()
    {
        Assert.assertEquals(FACILITIES, hotelApplication.getFacilities());
    }

    @Test
    public void setFacilitiesTest()
    {
        //arrange
        String newFacilities = "New Facilities";
        //act
        hotelApplication.setFacilities(newFacilities);
        //assert
        Assert.assertEquals(newFacilities, hotelApplication.getFacilities());
    }

    @Test
    public void getUserTest()
    {
        Assert.assertEquals(user, hotelApplication.getUserId());
    }

    @Test
    public void setUserTest()
    {
        //arrange
        User userToAssign = new User();
        //act
        hotelApplication.setUserId(userToAssign);
        //assert
        Assert.assertEquals(userToAssign, hotelApplication.getUserId());
    }

    @Test
    public void getStatusTest()
    {
        Assert.assertEquals(STATUS, hotelApplication.getStatus());
    }

    @Test
    public void setStatusTest()
    {
        //arrange
        String newStatus = "New Status";
        //act
        hotelApplication.setStatus(newStatus);
        //assert
        Assert.assertEquals(newStatus, hotelApplication.getStatus());
    }
}
