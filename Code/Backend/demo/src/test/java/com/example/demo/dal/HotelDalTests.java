package com.example.demo.dal;


import com.example.demo.dal.implementation.HotelDalJPA;
import com.example.demo.dao.HotelDao;
import com.example.demo.entity.Hotel;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {HotelDalJPA.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class HotelDalTests {

    @MockBean
    HotelDao hotelRepository;

    @InjectMocks
    HotelDalJPA hotelDal;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getHotelByIdTest()
    {
        //arrange
        Optional<Hotel> hotel = Optional.of(new Hotel(1L, "Name", new User()));
        //act
        when(hotelRepository.findById(any(Long.class))).thenReturn(hotel);
        Hotel created = hotelDal.getHotelById(hotel.get().getId());
        //assert
        Assert.assertEquals(Optional.of(created), hotel);
    }

    @Test
    public void getAllHotelsTest()
    {
        //arrange
        Hotel hotel1 = new Hotel(1L, "Name1", new User());
        Hotel hotel2 = new Hotel(2L, "Name2", new User());
        //act
        when(hotelRepository.findAll()).thenReturn(Arrays.asList(hotel1, hotel2));
        List<Hotel> all = hotelDal.getAllHotels();
        //assert
        Assert.assertEquals(all, Arrays.asList(hotel1, hotel2));
    }

    @Test
    public void addHotel()
    {
        //arrange
        Hotel hotel = new Hotel(1L, "Name", new User());
        //act
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);
        Hotel created = hotelDal.addHotel(hotel);
        //assert
        Assert.assertEquals(created, hotel);
    }
}
