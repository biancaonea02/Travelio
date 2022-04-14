package com.example.demo.service;

import com.example.demo.dal.IHotelApplicationDal;
import com.example.demo.entity.HotelApplication;
import com.example.demo.entity.User;
import com.example.demo.serviceInterfaces.IHotelApplicationService;
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
@SpringBootTest(classes = {HotelApplicationService.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class HotelApplicationServiceTests {

    @MockBean
    IHotelApplicationDal hotelApplicationDal;

//    @InjectMocks
//    HotelApplicationService hotelApplicationService = new HotelApplicationService(hotelApplicationDal);
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @Disabled
//    public void getHotelApplicationByIdTest()
//    {
//        //arrange
//        HotelApplication hotelApplication = new HotelApplication(1L, new User());
//        //act
//        when(hotelApplicationDal.getHotelApplicationById(any(Long.class))).thenReturn(hotelApplication);
//        HotelApplication created = hotelApplicationService.getHotelApplicationById(hotelApplication.getId());
//        //assert
//        Assert.assertEquals(created, hotelApplication);
//    }
//
//    @Test
//    @Disabled
//    public void getAllHotelApplicationsTest()
//    {
//        HotelApplication hotelApplication1 = new HotelApplication(1L, new User());
//        HotelApplication hotelApplication2 = new HotelApplication(2L, new User());
//        //act
//        when(hotelApplicationDal.getAllHotelApplications()).thenReturn(Arrays.asList(hotelApplication1, hotelApplication2));
//        List<HotelApplication> all = hotelApplicationService.getAllHotelApplications();
//        //assert
//        Assert.assertEquals(all, Arrays.asList(hotelApplication1, hotelApplication2));
//    }
//
//    @Test
//    @Disabled
//    public void addHotelApplicationTest()
//    {
//        //arrange
//        HotelApplication hotelApplication = new HotelApplication(1L, new User());
//        //act
//        when(hotelApplicationDal.addHotelApplication(any(HotelApplication.class))).thenReturn(hotelApplication);
//        HotelApplication created = hotelApplicationService.addHotelApplication(hotelApplication);
//        //assert
//        Assert.assertEquals(created, hotelApplication);
//    }
}
