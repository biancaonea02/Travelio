package com.example.demo.service;


import com.example.demo.dal.IReservationDal;
import com.example.demo.dal.IRoomDal;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
@SpringBootTest(classes = {RoomService.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {

    @MockBean
    IReservationDal reservationDal;

    @MockBean
    IRoomDal roomDal;

//    @InjectMocks
//    ReservationService reservationService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @Disabled
//    public void getReservationByIdTest()
//    {
//        //arrange
//        Reservation reservation = new Reservation(1L, new Hotel(), new User());
//        //act
//        when(reservationDal.getReservationById(any(Long.class))).thenReturn(reservation);
//        Reservation created = reservationService.getReservationById(reservation.getId());
//        //assert
//        Assert.assertEquals(created, reservation);
//    }
//
//    @Test
//    @Disabled
//    public void getAllReservationsTest()
//    {
//        //arrange
//        Reservation reservation1 = new Reservation(1L, new Hotel(), new User());
//        Reservation reservation2 = new Reservation(2L, new Hotel(), new User());
//        //act
//        when(reservationDal.getAllReservations()).thenReturn(Arrays.asList(reservation1, reservation2));
//        List<Reservation> all = reservationService.getAllReservations();
//        //assert
//        Assert.assertEquals(all, Arrays.asList(reservation1, reservation2));
//    }
//
//    @Test
//    @Disabled
//    public void addReservationTest()
//    {
//        //arrange
//        Long id = 1L;
//        Reservation reservation = new Reservation(id, new Hotel(), new User());
//        //act
//        when(reservationDal.addReservation(any(Reservation.class))).thenReturn(reservation);
//        Reservation created = reservationService.addReservation(reservation);
//        //assert
//        Assert.assertEquals(created, reservation);
//    }
//
//    @Test
//    @Disabled
//    public void getReservationsOfHotelTest()
//    {
//        //arrange
//        Hotel hotel = new Hotel();
//        hotel.setId(1L);
//        Reservation reservation1 = new Reservation(1L, hotel, new User());
//        Reservation reservation2 = new Reservation(2L, hotel, new User());
//        //act
//        when(reservationDal.getReservationsOfHotel(any(Long.class))).thenReturn(Arrays.asList(reservation1, reservation2));
//        List<Reservation> all = reservationService.getReservationsOfHotel(hotel.getId());
//        //assert
//        Assert.assertEquals(all, Arrays.asList(reservation1, reservation2));
//    }
//
//    @Test
//    @Disabled
//    public void getReservationsOfUserTest()
//    {
//        //arrange
//        User user = new User();
//        user.setId(1L);
//        Reservation reservation1 = new Reservation(1L, new Hotel(), user);
//        Reservation reservation2 = new Reservation(2L, new Hotel(), user);
//        //act
//        when(reservationDal.getReservationsOfUser(any(Long.class))).thenReturn(Arrays.asList(reservation1, reservation2));
//        List<Reservation> all = reservationService.getReservationsOfUser(user.getId());
//        //assert
//        Assert.assertEquals(all, Arrays.asList(reservation1, reservation2));
//    }
//
//    @Test
//    @Disabled
//    public void getReservationsOfHotelOwnerTest()
//    {
//        //arrange
//        User user = new User();
//        user.setId(1L);
//        Hotel hotel = new Hotel();
//        hotel.setOwner(user);
//        Reservation reservation1 = new Reservation(1L, hotel, new User());
//        Reservation reservation2 = new Reservation(2L, hotel, new User());
//        //act
//        when(reservationDal.getReservationsOfHotelOwner(any(Long.class))).thenReturn(Arrays.asList(reservation1, reservation2));
//        List<Reservation> all = reservationService.getReservationsOfHotelOwner(user.getId());
//        //assert
//        Assert.assertEquals(all, Arrays.asList(reservation1, reservation2));
//    }
}
