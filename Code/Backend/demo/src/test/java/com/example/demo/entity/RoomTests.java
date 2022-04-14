package com.example.demo.entity;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RoomTests {

    //room data
    public Room room;
    public static final Long ROOM_ID = 1L;
    public static final int CAPACITY = 3;
    public static final double PRICE_PER_NIGHT = 100;
    public static final String TYPE = "single";
    public static final Boolean RESERVED = false;
    public static final int DIMENSION = 28;
    public static final String FACILITIES_ROOM = "Test Facilities";

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

    private final Hotel hotel = new Hotel(HOTEL_ID, NAME, CITY, COUNTRY, ADDRESS, RATING, PHONE_NR, FREE_PARKING, FREE_CANCELLATION,
            TOP_LOCATION, DESCRIPTION, FACILITIES_HOTEL, new User());


    @BeforeEach
    void setUp() {
      room = new Room(ROOM_ID, hotel, CAPACITY, PRICE_PER_NIGHT, TYPE, RESERVED, DIMENSION, FACILITIES_ROOM);
    }


    @Test
    public void getIdTest()
    {
        Assert.assertEquals(ROOM_ID, room.getId());
    }

    @Test
    public void setRoomTest()
    {
        //arrange
        Long newId = 2L;
        //act
        room.setId(newId);
        //assert
        Assert.assertEquals(newId, room.getId());
    }

    @Test
    public void getHotelTest()
    {
        Assert.assertEquals(hotel, room.getHotelId());
    }

    @Test
    public void setHotelTest()
    {
        //arrange
        Hotel hotelToAssign = new Hotel();
        //act
        room.setHotelId(hotelToAssign);
        //assert
        Assert.assertEquals(hotelToAssign, room.getHotelId());
    }

    @Test
    public void getCapacityTest()
    {
        Assert.assertEquals(CAPACITY, room.getCapacity());
    }

    @Test
    public void setCapacityTest()
    {
        //arrange
        int newCapacity = 3;
        //act
        room.setCapacity(newCapacity);
        //assert
        Assert.assertEquals(newCapacity, room.getCapacity());
    }

    @Test
    public void getPricePerNightTest()
    {
        Assertions.assertEquals(PRICE_PER_NIGHT, room.getPricePerNight());
    }

    @Test
    public void setPricePerNightTest()
    {
        //arrange
        int newPricePerNight = 100;
        //act
        room.setPricePerNight(newPricePerNight);
        //assert
        Assertions.assertEquals(newPricePerNight, room.getPricePerNight());
    }

    @Test
    public void getReservedTest()
    {
        Assertions.assertEquals(RESERVED, room.getReserved());
    }

    @Test
    public void setReservedTest()
    {
        //arrange

        //act
        room.setReserved(true);
        //assert
        Assert.assertEquals(true, room.getReserved());
    }

    @Test
    public void getDimensionTest()
    {
        Assertions.assertEquals(DIMENSION, room.getDimension());
    }

    @Test
    public void setDimensionTest()
    {
        //arrange
        int newDimension = 25;
        //act
        room.setDimension(newDimension);
        //assert
        Assert.assertEquals(newDimension, room.getDimension());
    }

    @Test
    public void getFacilitiesTest()
    {
        Assertions.assertEquals(FACILITIES_ROOM, room.getFacilities());
    }

    @Test
    public void setFacilitiesTest()
    {
        //arrange
        String newFacilities = "New Facilities";
        //act
        room.setFacilities(newFacilities);
        //assert
        Assert.assertEquals(newFacilities, room.getFacilities());
    }
}
