package com.example.demo.service;

import com.example.demo.dal.IReservationDal;
import com.example.demo.dal.IRoomDal;
import com.example.demo.entity.Reservation;
import com.example.demo.serviceInterfaces.IHotelApplicationService;
import com.example.demo.serviceInterfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService{

    @Autowired
    private IReservationDal iReservationDal;

    @Autowired
    private IRoomDal iRoomDal;

    @Autowired
    private IHotelApplicationService iHotelApplicationService;

    public ReservationService(IReservationDal iReservationDal, IRoomDal iRoomDal, IHotelApplicationService iHotelApplicationService)
    {
        this.iReservationDal = iReservationDal;
        this.iRoomDal = iRoomDal;
        this.iHotelApplicationService = iHotelApplicationService;
    }

    @Override
    public Reservation getReservationById(Long id) {
        return iReservationDal.getReservationById(id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return iReservationDal.getAllReservations();
    }

    @Override
    public Reservation addReservation(Reservation reservation)
    {
        return iReservationDal.addReservation(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        iReservationDal.deleteReservation(id);
    }

    @Override
    public List<Reservation> getReservationsOfHotel(Long idHotel) {
        return iReservationDal.getReservationsOfHotel(idHotel);
    }

    @Override
    public List<Reservation> getReservationsOfUser(Long idUser) {
        return iReservationDal.getReservationsOfUser(idUser);
    }

    @Override
    public List<Reservation> getLastFiveReservations() {
        return iReservationDal.getLastFiveReservations();
    }

    @Override
    public List<Reservation> getReservationsOfHotelOwner(Long userId) {
        return iReservationDal.getReservationsOfHotelOwner(userId);
    }
}
