package com.example.demo.dao;

import com.example.demo.entity.*;
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
public class FavouriteHotelDaoTests {

    @Mock
    FavouriteHotelDao favouriteHotelRepository;

    @Test
    public void getByIdTest()
    {
        //arrange
        Long id = 1L;
        FavouriteHotel favouriteHotel = new FavouriteHotel(id, new Hotel(), new User());
        //act
        when(favouriteHotelRepository.getById(1L)).thenReturn(favouriteHotel);
        //assert
        Assert.assertEquals(id, favouriteHotelRepository.getById(1L).getId());
    }

    @Test
    public void getAllFavouriteHotelsTest()
    {
        FavouriteHotel favouriteHotel1 = new FavouriteHotel(1L, new Hotel(), new User());
        FavouriteHotel favouriteHotel2 = new FavouriteHotel(2L, new Hotel(), new User());
        //act
        when(favouriteHotelRepository.findAll()).thenReturn(Arrays.asList(favouriteHotel1, favouriteHotel2));
        //assert
        Assert.assertEquals(Arrays.asList(favouriteHotel1, favouriteHotel2), favouriteHotelRepository.findAll());
    }

    @Test
    public void addFavouriteHotelTest()
    {
        //arrange
        FavouriteHotel favouriteHotel = new FavouriteHotel(1L, new Hotel(), new User());
        //act
        when(favouriteHotelRepository.save(any(FavouriteHotel.class))).thenReturn(favouriteHotel);
        //assert
        Assert.assertEquals(favouriteHotel, favouriteHotelRepository.save(favouriteHotel));
    }

}
