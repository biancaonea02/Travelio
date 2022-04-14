package com.example.demo.controller;

import com.example.demo.entity.FavouriteHotel;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.User;
import com.example.demo.serviceInterfaces.IFavouriteHotelService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = {FavouriteHotelController.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(FavouriteHotelController.class)
public class FavouriteHotelControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IFavouriteHotelService favouriteHotelService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser
    public void getFavouriteHotelByIdTest() throws Exception {
        //arrange
        FavouriteHotel favouriteHotel = new FavouriteHotel();
        favouriteHotel.setId(1L);
        Mockito.when(favouriteHotelService.getFavouriteHotelById(favouriteHotel.getId())).thenReturn(favouriteHotel);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/favouriteHotel/{id}",favouriteHotel.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getAllFavouriteHotelsTest() throws Exception {
        //arrange
        FavouriteHotel favouriteHotel1 = new FavouriteHotel(1L, new Hotel(), new User());
        FavouriteHotel favouriteHotel2 = new FavouriteHotel(2L, new Hotel(), new User());
        List<FavouriteHotel> favouriteHotels = new ArrayList<>(Arrays.asList(favouriteHotel1, favouriteHotel2));
        Mockito.when(favouriteHotelService.getAllFavouriteHotels()).thenReturn(favouriteHotels);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/favouriteHotel")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getFavouriteHotelsOfUserTest() throws Exception {
        //arrange
        User user = new User();
        user.setId(1L);
        FavouriteHotel favouriteHotel1 = new FavouriteHotel(1L, new Hotel(), user);
        FavouriteHotel favouriteHotel2 = new FavouriteHotel(2L, new Hotel(), user);
        List<Hotel> favouriteHotels = new ArrayList<>(Arrays.asList(favouriteHotel1.getHotelId(), favouriteHotel2.getHotelId()));
        Mockito.when(favouriteHotelService.getHotelsLikedByUser(user.getId())).thenReturn(favouriteHotels);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/favouriteHotel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("idUser", user.getId().toString()))
                //assert
                .andExpect(status().isOk());
    }

}
