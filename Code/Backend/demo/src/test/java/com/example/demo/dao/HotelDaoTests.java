package com.example.demo.dao;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class HotelDaoTests {

    @Mock
    HotelDao hotelRepository;

    @Test
    public void getHotelByIdTest()
    {
        //arrange
        Long id = 1L;
        Hotel hotel = new Hotel(id, "Name", new User());
        //act
        when(hotelRepository.getById(1L)).thenReturn(hotel);
        //assert
        Assert.assertEquals(id, hotelRepository.getById(1L).getId());
    }

    @Test
    public void getAllHotelsTest()
    {
        //arrange
        Hotel hotel1 = new Hotel(1L, "Name1", new User());
        Hotel hotel2 = new Hotel(2L, "Name2", new User());
        //act
        when(hotelRepository.findAll()).thenReturn(Arrays.asList(hotel1, hotel2));
        //assert
        Assert.assertEquals(Arrays.asList(hotel1, hotel2), hotelRepository.findAll());
    }

    @Test
    public void addHotel()
    {
        //arrange
        Hotel hotel = new Hotel(1L, "Name", new User());
        //act
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);
        //assert
        Assert.assertEquals(hotel, hotelRepository.save(hotel));
    }
}
