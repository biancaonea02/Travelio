package com.example.demo.controller;


import com.example.demo.entity.Hotel;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;
import com.example.demo.serviceInterfaces.IReservationService;
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
@SpringBootTest(classes = {ReservationController.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(ReservationController.class)
public class ReservationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IReservationService reservationService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser
    public void getAllReservationsTest() throws Exception {
        //arrange
        Reservation reservation1 = new Reservation(1L, new Hotel(), new User());
        Reservation reservation2 = new Reservation(2L, new Hotel(), new User());
        List<Reservation> reservations = new ArrayList<>(Arrays.asList(reservation1, reservation2));
        Mockito.when(reservationService.getAllReservations()).thenReturn(reservations);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/reservation")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getReservationsOfHotelTest() throws Exception {
        //arrange
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        Reservation reservation1 = new Reservation(1L, hotel, new User());
        Reservation reservation2 = new Reservation(2L, hotel, new User());
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);
        when(reservationService.getReservationsOfHotel(hotel.getId())).thenReturn(reservations);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/reservation/getHotelReservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("idHotel", hotel.getId().toString())
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void getReservationsOfUserTest() throws Exception {
        //arrange
        //arrange
        User user = new User();
        user.setId(1L);
        Reservation reservation1 = new Reservation(1L, new Hotel(), user);
        Reservation reservation2 = new Reservation(2L, new Hotel(), user);
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);
        when(reservationService.getReservationsOfUser(user.getId())).thenReturn(reservations);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/reservation/getUserReservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("idUser", user.getId().toString())
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void getReservationsOfHotelOwnerTest() throws Exception {
        //arrange
        //arrange
        User user = new User();
        user.setId(1L);
        Hotel hotel = new Hotel();
        hotel.setOwner(user);
        Reservation reservation1 = new Reservation(1L, hotel, new User());
        Reservation reservation2 = new Reservation(2L, hotel, new User());
        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);
        when(reservationService.getReservationsOfHotelOwner(user.getId())).thenReturn(reservations);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/reservation/getReservationsOfHotelOwner/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());

    }
}
