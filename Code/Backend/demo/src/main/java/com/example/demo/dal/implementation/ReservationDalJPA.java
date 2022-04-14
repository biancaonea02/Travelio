package com.example.demo.dal.implementation;

import com.example.demo.dal.IReservationDal;
import com.example.demo.dao.ReservationDao;
import com.example.demo.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationDalJPA implements IReservationDal {

    @Autowired
    ReservationDao reservationDao;


    @Override
    public Reservation getReservationById(Long id) {
        return reservationDao.findById(id).get();
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationDao.findAll();
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationDao.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationDao.deleteById(id);
    }

    @Override
    public List<Reservation> getReservationsOfHotel(Long idHotel) {
        List<Reservation> reservations = new ArrayList<>();
        for(Reservation reservation : reservationDao.findAll())
        {
            if(reservation.getHotelId().getId().equals(idHotel))
            {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsOfUser(Long idUser) {
        List<Reservation> reservations = new ArrayList<>();
        for(Reservation reservation : reservationDao.findAll())
        {
            if(reservation.getUserId().getId() == idUser)
            {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    @Override
    public List<Reservation> getLastFiveReservations() {
        List<Reservation> lastFive = new ArrayList<>();
        int i;
        for(i = reservationDao.findAll().size() - 1; i >= reservationDao.findAll().size() - 5; i--)
        {
            lastFive.add(reservationDao.findAll().get(i));
        }
        return lastFive;
    }

    @Override
    public List<Reservation> getReservationsOfHotelOwner(Long userId) {
        List<Reservation> reservations = new ArrayList<>();
        for(Reservation reservation : reservationDao.findAll())
        {
            if(reservation.getHotelId().getOwner().getId().equals(userId))
            {
                reservations.add(reservation);
            }
        }

        return reservations;
    }
}
