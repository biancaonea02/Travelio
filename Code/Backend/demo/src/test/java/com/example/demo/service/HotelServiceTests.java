package com.example.demo.service;

import com.example.demo.dal.IHotelDal;
import com.example.demo.dal.IUserDal;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.Before;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {HotelService.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class HotelServiceTests {

    @MockBean
    IHotelDal hotelDal;

//    @InjectMocks
//    HotelService hotelService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @Disabled
//    public void getHotelByIdTest()
//    {
//        //arrange
//        Hotel hotel = new Hotel(1L, "Name", new User());
//        //act
//        when(hotelDal.getHotelById(any(Long.class))).thenReturn(hotel);
//        Hotel created = hotelService.getHotelById(hotel.getId());
//        //assert
//        Assert.assertEquals(created, hotel);
//    }
//
//    @Test
//    @Disabled
//    public void getAllHotelsTest()
//    {
//        //arrange
//        Hotel hotel1 = new Hotel(1L, "Name1", new User());
//        Hotel hotel2 = new Hotel(2L, "Name2", new User());
//        //act
//        when(hotelDal.getAllHotels()).thenReturn(Arrays.asList(hotel1, hotel2));
//        List<Hotel> all = hotelService.getAllHotels();
//        //assert
//        Assert.assertEquals(all, Arrays.asList(hotel1, hotel2));
//    }
//
//    @Test
//    @Disabled
//    public void addHotel()
//    {
//        //arrange
//        Hotel hotel = new Hotel(1L, "Name", new User());
//        //act
//        when(hotelDal.addHotel(any(Hotel.class))).thenReturn(hotel);
//        Hotel created = hotelDal.addHotel(hotel);
//        //assert
//        Assert.assertEquals(created, hotel);
//    }
//
//    @Test
//    @Disabled
//    public void getHotelsOfCityTest()
//    {
//        //arrange
//        Hotel hotel1 = new Hotel(1L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
//                true, "DESCRIPTION", "FACILITIES", new User());
//        Hotel hotel2 = new Hotel(2L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
//                true, "DESCRIPTION", "FACILITIES", new User());
//        //act
//        when(hotelService.getHotelsOfCity("City")).thenReturn(Arrays.asList(hotel1, hotel2));
//        //assert
//        Assert.assertEquals(Arrays.asList(hotel1, hotel2), hotelService.getHotelsOfCity("Bucharest"));
//    }
//
//    @Test
//    @Disabled
//    public void getHotelsOfOwnerTest()
//    {
//        //arrange
//        User user = new User(1L, "First Name", "Last Name", "Username", "Email");
//        Hotel hotel1 = new Hotel(1L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
//                true, "DESCRIPTION", "FACILITIES", user);
//        Hotel hotel2 = new Hotel(2L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
//                true, "DESCRIPTION", "FACILITIES", user);
//        //act
//        when(hotelDal.getHotelsOfOwner(any(Long.class))).thenReturn(Arrays.asList(hotel1, hotel2));
//        List<Hotel> ofOwner = hotelService.getHotelsOfOwner(user.getId());
//        //assert
//        Assert.assertEquals(ofOwner, Arrays.asList(hotel1, hotel2));
//    }
//
//    @Test
//    @Disabled
//    public void getHotelsByRatingTest()
//    {
//        //arrange
//        Hotel hotel1 = new Hotel(1L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
//                true, "DESCRIPTION", "FACILITIES", new User());
//        //act
//        when(hotelService.getHotelsByRating(1)).thenReturn(Arrays.asList(hotel1));
//        //assert
//        Assert.assertEquals(Arrays.asList(hotel1), hotelService.getHotelsByRating(3));
//    }
//
//    @Test
//    @Disabled
//    public void updateHotel()
//    {
//        //arrange
//        Hotel hotel1 = new Hotel(1L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
//                true, "DESCRIPTION", "FACILITIES", new User());
//        //act
//        when(hotelService.updateHotel(1L, "Name", 4, "Phone Nr", true, true, "Description", "Facilities")).thenReturn(hotel1);
//        //assert
//        Assert.assertEquals("Name", hotelService.updateHotel(hotel1.getId(), "Name", 4, "Phone Nr", true, true, "Description", "Facilities").getName());
//    }
}
