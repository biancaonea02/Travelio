package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.User;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = {HotelController.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(HotelController.class)
public class HotelControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IHotelService hotelService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser
    public void getHotelByIdTest() throws Exception {
        //arrange
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        Mockito.when(hotelService.getHotelById(hotel.getId())).thenReturn(hotel);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hotel/{id}",hotel.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getAllHotelsTest() throws Exception {
        //arrange
        Hotel hotel1 = new Hotel(1L, "Name1", new User());
        Hotel hotel2 = new Hotel(2L, "Name2", new User());
        List<Hotel> hotels = new ArrayList<>(Arrays.asList(hotel1, hotel2));
        Mockito.when(hotelService.getAllHotels()).thenReturn(hotels);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hotel")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getHotelsByNrStars() throws Exception {
        //arrange
        Hotel hotel1 = new Hotel(1L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
                true, "DESCRIPTION", "FACILITIES", new User());
        Hotel hotel2 = new Hotel(2L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
                true, "DESCRIPTION", "FACILITIES", new User());
        List<Hotel> hotels = new ArrayList<>(Arrays.asList(hotel1, hotel2));
        when(hotelService.getHotelsByRating(3)).thenReturn(hotels);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hotel/filterByStars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("rating", "3")
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void getHotelsOfCity() throws Exception {
        //arrange
        Hotel hotel1 = new Hotel(1L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
                true, "DESCRIPTION", "FACILITIES", new User());
        Hotel hotel2 = new Hotel(2L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
                true, "DESCRIPTION", "FACILITIES", new User());
        List<Hotel> hotels = new ArrayList<>(Arrays.asList(hotel1, hotel2));
        when(hotelService.getHotelsOfCity("Bucharest")).thenReturn(hotels);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hotel/numberOfHotelsOfCity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("city", "Bucharest")
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void getHotelsOfOwner() throws Exception {
        //arrange
        User user = new User();
        user.setId(1L);
        Hotel hotel1 = new Hotel(1L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
                true, "DESCRIPTION", "FACILITIES", user);
        Hotel hotel2 = new Hotel(2L, "NAME", "Bucharest", "COUNTRY", "ADDRESS", 3, "PHONE_NR", true, true,
                true, "DESCRIPTION", "FACILITIES", user);
        List<Hotel> hotels = new ArrayList<>(Arrays.asList(hotel1, hotel2));
        when(hotelService.getHotelsOfOwner(user.getId())).thenReturn(hotels);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hotel/hotelsOfOwner/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());

    }


}
