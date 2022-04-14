package com.example.demo.controller;

import com.example.demo.dal.IHotelApplicationDal;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.HotelApplication;
import com.example.demo.entity.User;
import com.example.demo.serviceInterfaces.IHotelApplicationService;
import com.example.demo.serviceInterfaces.IHotelService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = {HotelApplicationController.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(HotelApplicationController.class)
public class HotelApplicationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IHotelApplicationService hotelApplicationService;

    @MockBean
    IHotelService hotelService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser
    public void getHotelApplicationByIdTest() throws Exception {
        //arrange
        HotelApplication hotelApplication = new HotelApplication();
        hotelApplication.setId(1L);
        Mockito.when(hotelApplicationService.getHotelApplicationById(hotelApplication.getId())).thenReturn(hotelApplication);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hotelApplication/{id}",hotelApplication.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getAllHotelApplicationsTest() throws Exception {
        //arrange
        HotelApplication hotelApplication1 = new HotelApplication(1L, new User());
        HotelApplication hotelApplication2 = new HotelApplication(2L, new User());
        //act
        Mockito.when(hotelApplicationService.getAllHotelApplications()).thenReturn(Arrays.asList(hotelApplication1, hotelApplication2));
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hotelApplication")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getHotelApplicationsOfUser() throws Exception {
        //arrange
        User user = new User();
        user.setId(1L);
        HotelApplication hotelApplication1 = new HotelApplication(1L, user);
        HotelApplication hotelApplication2 = new HotelApplication(2L, user);
        //act
        Mockito.when(hotelApplicationService.getHotelApplicationsOfUser(user.getId())).thenReturn(Arrays.asList(hotelApplication1, hotelApplication2));
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hotelApplication/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", user.getId().toString()))
                //assert
                .andExpect(status().isOk());
    }
}
