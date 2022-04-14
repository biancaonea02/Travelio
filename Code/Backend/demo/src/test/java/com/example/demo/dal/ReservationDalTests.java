package com.example.demo.dal;

import com.example.demo.dal.implementation.ReservationDalJPA;
import com.example.demo.dao.ReservationDao;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Reservation;
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
@SpringBootTest(classes = {ReservationDalJPA.class})
@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
public class ReservationDalTests {

    @MockBean
    ReservationDao reservationRepository;

    @InjectMocks
    ReservationDalJPA reservationDal;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getReservationByIdTest()
    {
        //arrange
        Optional<Reservation> reservation = Optional.of(new Reservation(1L, new Hotel(), new User()));
        //act
        when(reservationRepository.findById(any(Long.class))).thenReturn(reservation);
        Reservation created = reservationDal.getReservationById(reservation.get().getId());
        //assert
        Assert.assertEquals(Optional.of(created), reservation);
    }

    @Test
    public void getAllReservationsTest()
    {
        //arrange
        Reservation reservation1 = new Reservation(1L, new Hotel(), new User());
        Reservation reservation2 = new Reservation(2L, new Hotel(), new User());
        //act
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));
        List<Reservation> all = reservationDal.getAllReservations();
        //assert
        Assert.assertEquals(all, Arrays.asList(reservation1, reservation2));
    }

    @Test
    public void addReservationTest()
    {
        //arrange
        Long id = 1L;
        Reservation reservation = new Reservation(id, new Hotel(), new User());
        //act
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        Reservation created = reservationDal.addReservation(reservation);
        //assert
        Assert.assertEquals(created, reservation);
    }

}
