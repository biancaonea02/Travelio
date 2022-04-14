package com.example.demo.dao;

import com.example.demo.entity.HotelApplication;
import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class HotelApplicationDaoTests {

    @Mock
    private HotelApplicationDao hotelApplicationRepository;

    @Test
    public void getByIdTest()
    {
        //arrange
        Long id = 1L;
        HotelApplication hotelApplication = new HotelApplication(id, new User());
        //act
        when(hotelApplicationRepository.getById(1L)).thenReturn(hotelApplication);
        //assert
        Assert.assertEquals(id, hotelApplicationRepository.getById(1L).getId());
    }

    @Test
    public void getAllHotelApplicationsTest()
    {
        HotelApplication hotelApplication1 = new HotelApplication(1L, new User());
        HotelApplication hotelApplication2 = new HotelApplication(2L, new User());
        //act
        when(hotelApplicationRepository.findAll()).thenReturn(Arrays.asList(hotelApplication1, hotelApplication2));
        //assert
        Assert.assertEquals(Arrays.asList(hotelApplication1, hotelApplication2), hotelApplicationRepository.findAll());
    }

    @Test
    public void addHotelApplicationsTest()
    {
        //arrange
        HotelApplication hotelApplication = new HotelApplication(1L, new User());
        //act
        when(hotelApplicationRepository.save(any(HotelApplication.class))).thenReturn(hotelApplication);
        //assert
        Assert.assertEquals(hotelApplication, hotelApplicationRepository.save(hotelApplication));
    }
}

