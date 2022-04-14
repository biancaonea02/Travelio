package com.example.demo.entity;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HotelTests {

    public Hotel hotel;
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


    @BeforeEach
    void setUp(){
        hotel = new Hotel(HOTEL_ID, NAME, CITY, COUNTRY, ADDRESS, RATING, PHONE_NR, FREE_PARKING, FREE_CANCELLATION,
                TOP_LOCATION, DESCRIPTION, FACILITIES, new User());
    }


    @Test
    public void getIdTest()
    {
        Assert.assertEquals(HOTEL_ID, hotel.getId());
    }

    @Test
    public void setIdTest()
    {
        //arrange
        Long newId = 2L;
        //act
        hotel.setId(newId);
        //assert
        Assert.assertEquals(newId, hotel.getId());
    }

    @Test
    public void getNameTest()
    {
        Assert.assertEquals(NAME, hotel.getName());
    }

    @Test
    public void setNameTest()
    {
        //arrange
        String newName = "New Name";
        //act
        hotel.setName(newName);
        //assert
        Assert.assertEquals(newName, hotel.getName());
    }

    @Test
    public void getCityTest()
    {
        Assert.assertEquals(CITY, hotel.getCity());
    }

    @Test
    public void setCityTest()
    {
        //arrange
        String newCity = "New City";
        //act
        hotel.setCity(newCity);
        //assert
        Assert.assertEquals(newCity, hotel.getCity());
    }

    @Test
    public void getCountryTest()
    {
        Assert.assertEquals(COUNTRY, hotel.getCountry());
    }

    @Test
    public void setCountryTest()
    {
        //arrange
        String newCountry = "New Country";
        //act
        hotel.setCountry(newCountry);
        //assert
        Assert.assertEquals(newCountry, hotel.getCountry());
    }

    @Test
    public void getAddressTest()
    {
        Assert.assertEquals(ADDRESS, hotel.getAddress());
    }

    @Test
    public void setAddressTest()
    {
        //arrange
        String newAddress = "New Address";
        //act
        hotel.setAddress(newAddress);
        //assert
        Assert.assertEquals(newAddress, hotel.getAddress());
    }

    @Test
    public void getRatingTest()
    {
        Assert.assertEquals(RATING, hotel.getRating());
    }

    @Test
    public void setRatingTest()
    {
        //arrange
        int newRating = 4;
        //act
        hotel.setRating(newRating);
        //assert
        Assert.assertEquals(newRating, hotel.getRating());
    }

    @Test
    public void getPhoneNumberTest()
    {
        Assert.assertEquals(PHONE_NR, hotel.getPhoneNumber());
    }

    @Test
    public void setPhoneNumberTest()
    {
        //arrange
        String newPhoneNr = "New Phone Nr";
        //act
        hotel.setPhoneNumber(newPhoneNr);
        //assert
        Assert.assertEquals(newPhoneNr, hotel.getPhoneNumber());
    }

    @Test
    public void getFreeParkingTest()
    {
        Assert.assertEquals(FREE_PARKING, hotel.getFreeParking());
    }

    @Test
    public void setFreeParkingTest()
    {
        //arrange

        //act
        hotel.setFreeParking(false);
        //assert
        Assert.assertEquals(false, hotel.getFreeParking());
    }

    @Test
    public void getFreeCancellationTest()
    {
        Assert.assertEquals(true, hotel.getFreeCancellation());
    }

    @Test
    public void setFreeCancellationTest()
    {
        //arrange

        //act
        hotel.setFreeCancellation(false);
        //assert
        Assert.assertEquals(false, hotel.getFreeCancellation());
    }

    @Test
    public void getTopLocationTest()
    {
        Assert.assertEquals(true, hotel.getTopLocation());
    }

    @Test
    public void setTopLocationTest()
    {
        //arrange

        //act
        hotel.setTopLocation(true);
        //assert
        Assert.assertEquals(true, hotel.getTopLocation());
    }

    @Test
    public void getDescriptionTest()
    {
        Assert.assertEquals(DESCRIPTION, hotel.getDescription());
    }

    @Test
    public void setDescriptionTest()
    {
        //arrange
        String newDescription = "New Description";
        //act
        hotel.setDescription(newDescription);
        //assert
        Assert.assertEquals(newDescription, hotel.getDescription());
    }

    @Test
    public void getFacilitiesTest()
    {
        Assert.assertEquals(FACILITIES, hotel.getFacilities());
    }

    @Test
    public void setFacilitiesTest()
    {
        //arrange
        String newFacilities = "New Facilities";
        //act
        hotel.setFacilities(newFacilities);
        //assert
        Assert.assertEquals(newFacilities, hotel.getFacilities());
    }
}
