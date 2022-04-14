package com.example.demo.dal;

import com.example.demo.TestApplication;
import com.example.demo.dal.implementation.FavouriteHotelDalJPA;
import com.example.demo.dal.implementation.HotelApplicationDalJPA;
import com.example.demo.dao.FavouriteHotelDao;
import com.example.demo.dao.HotelApplicationDao;
import com.example.demo.entity.FavouriteHotel;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {FavouriteHotelDalJPA.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class FavouriteHotelDalTests {

    @MockBean
    FavouriteHotelDao favouriteHotelRepository;

    @InjectMocks
    FavouriteHotelDalJPA favouriteHotelDal;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getFavouriteHotelByIdTest()
    {
        //arrange
        FavouriteHotel favouriteHotel = new FavouriteHotel(1L, new Hotel(), new User());
        //act
        when(favouriteHotelRepository.getById(1L)).thenReturn(favouriteHotel);
        FavouriteHotel found = favouriteHotelDal.getFavouriteHotelById(favouriteHotel.getId());
        //assert
        Assert.assertEquals(found, favouriteHotel);
    }

    @Test
    public void getAllFavouriteHotelsTest()
    {
        FavouriteHotel favouriteHotel1 = new FavouriteHotel(1L, new Hotel(), new User());
        FavouriteHotel favouriteHotel2 = new FavouriteHotel(2L, new Hotel(), new User());
        //act
        when(favouriteHotelRepository.findAll()).thenReturn(Arrays.asList(favouriteHotel1, favouriteHotel2));
        List<FavouriteHotel> all = favouriteHotelDal.getAllFavouriteHotels();
        //assert
        Assert.assertEquals(all, Arrays.asList(favouriteHotel1, favouriteHotel2));
    }

    @Test
    public void addHotelToFavouritesTest()
    {
        //arrange
        FavouriteHotel favouriteHotel = new FavouriteHotel(1L, new Hotel(), new User());
        //act
        when(favouriteHotelRepository.save(any(FavouriteHotel.class))).thenReturn(favouriteHotel);
        FavouriteHotel created = favouriteHotelDal.addHotelToFavourites(favouriteHotel);
        //assert
        Assert.assertEquals(created, favouriteHotel);
    }

}
