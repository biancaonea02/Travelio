package com.example.demo.service;

import com.example.demo.dal.IFavouriteHotelDal;
import com.example.demo.entity.FavouriteHotel;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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
@SpringBootTest(classes = {FavouriteHotelService.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class FavouriteHotelServiceTests {

    @MockBean
    IFavouriteHotelDal favouriteHotelDal;

//    @Autowired
//    FavouriteHotelService favouriteHotelService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @Disabled
//    public void getFavouriteHotelByIdTest()
//    {
//        //arrange
//        FavouriteHotel favouriteHotel = new FavouriteHotel(1L, new Hotel(), new User());
//        //act
//        when(favouriteHotelDal.getFavouriteHotelById(1L)).thenReturn(favouriteHotel);
//        FavouriteHotel found = favouriteHotelService.getFavouriteHotelById(favouriteHotel.getId());
//        //assert
//        Assert.assertEquals(found, favouriteHotel);
//    }
//
//    @Test
//    @Disabled
//    public void getAllFavouriteHotelsTest()
//    {
//        FavouriteHotel favouriteHotel1 = new FavouriteHotel(1L, new Hotel(), new User());
//        FavouriteHotel favouriteHotel2 = new FavouriteHotel(2L, new Hotel(), new User());
//        //act
//        when(favouriteHotelDal.getAllFavouriteHotels()).thenReturn(Arrays.asList(favouriteHotel1, favouriteHotel2));
//        List<FavouriteHotel> all = favouriteHotelService.getAllFavouriteHotels();
//        //assert
//        Assert.assertEquals(all, Arrays.asList(favouriteHotel1, favouriteHotel2));
//    }
//
//    @Test
//    @Disabled
//    public void addHotelToFavouritesTest()
//    {
//        //arrange
//        FavouriteHotel favouriteHotel = new FavouriteHotel(1L, new Hotel(), new User());
//        //act
//        when(favouriteHotelDal.addHotelToFavourites(any(FavouriteHotel.class))).thenReturn(favouriteHotel);
//        FavouriteHotel created = favouriteHotelService.addHotelToFavourites(favouriteHotel);
//        //assert
//        Assert.assertEquals(created, favouriteHotel);
//    }

}
