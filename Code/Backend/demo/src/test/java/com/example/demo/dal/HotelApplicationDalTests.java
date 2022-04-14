package com.example.demo.dal;

import com.example.demo.dal.implementation.HotelApplicationDalJPA;
import com.example.demo.dao.HotelApplicationDao;
import com.example.demo.entity.HotelApplication;
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
@SpringBootTest(classes = {HotelApplicationDalJPA.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class HotelApplicationDalTests {

    @MockBean
    HotelApplicationDao hotelApplicationRepository;

    @InjectMocks
    HotelApplicationDalJPA hotelApplicationDal;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getHotelApplicationByIdTest()
    {
        //arrange
        Optional<HotelApplication> hotelApplication = Optional.of(new HotelApplication(1L, new User()));
        //act
        when(hotelApplicationRepository.findById(1L)).thenReturn(hotelApplication);
        HotelApplication created = hotelApplicationDal.getHotelApplicationById(hotelApplication.get().getId());
        //assert
        Assert.assertEquals(Optional.of(created), hotelApplication);
    }

    @Test
    public void getAllHotelApplicationsTest()
    {
        HotelApplication hotelApplication1 = new HotelApplication(1L, new User());
        HotelApplication hotelApplication2 = new HotelApplication(2L, new User());
        //act
        when(hotelApplicationRepository.findAll()).thenReturn(Arrays.asList(hotelApplication1, hotelApplication2));
        List<HotelApplication> all = hotelApplicationDal.getAllHotelApplications();
        //assert
        Assert.assertEquals(all, Arrays.asList(hotelApplication1, hotelApplication2));
    }

    @Test
    public void addHotelApplicationTest()
    {
        //arrange
        HotelApplication hotelApplication = new HotelApplication(1L, new User());
        //act
        when(hotelApplicationRepository.save(any(HotelApplication.class))).thenReturn(hotelApplication);
        HotelApplication created = hotelApplicationDal.addHotelApplication(hotelApplication);
        //assert
        Assert.assertEquals(created, hotelApplication);
    }

}
