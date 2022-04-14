package com.example.demo.dal;

import com.example.demo.entity.Reservation;

import java.util.List;

public interface IReservationDal {
    Reservation getReservationById(Long id);
    List<Reservation> getAllReservations();
    Reservation addReservation(Reservation reservation);
    void deleteReservation(Long id);
    List<Reservation> getReservationsOfHotel(Long idHotel);
    List<Reservation> getReservationsOfUser(Long idUser);
    List<Reservation> getLastFiveReservations();
    List<Reservation> getReservationsOfHotelOwner(Long userId);
}
