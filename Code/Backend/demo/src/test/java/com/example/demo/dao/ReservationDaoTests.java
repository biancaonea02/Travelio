package com.example.demo.dao;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class ReservationDaoTests {

    @Mock
    ReservationDao reservationRepository;

    @Test
    public void getReservationByIdTest()
    {
        //arrange
        Long id = 1L;
        Reservation reservation = new Reservation(id, new Hotel(), new User());
        //act
        when(reservationRepository.getById(1L)).thenReturn(reservation);
        //assert
        Assert.assertEquals(id, reservationRepository.getById(1L).getId());

    }

    @Test
    public void getAllTest()
    {
        //arrange
        Reservation reservation1 = new Reservation(1L, new Hotel(), new User());
        Reservation reservation2 = new Reservation(2L, new Hotel(), new User());
        //act
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));
        //assert
        Assert.assertEquals(Arrays.asList(reservation1, reservation2), reservationRepository.findAll());
    }

    @Test
    public void saveReservationTest() {
        //arrange
        Long id = 1L;
        Reservation reservation = new Reservation(id, new Hotel(), new User());
        //act
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        //assert
        Assert.assertEquals(reservation, reservationRepository.save(reservation));
    }
}

