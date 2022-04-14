package com.example.demo.service;

import com.example.demo.dal.IReservationDal;
import com.example.demo.dal.IRoomDal;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Room;
import com.example.demo.serviceInterfaces.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomDal iRoomDal;

    @Autowired
    private IReservationDal iReservationDal;

    public RoomService(IRoomDal iRoomDal, IReservationDal iReservationDal)
    {
        this.iRoomDal = iRoomDal;
        this.iReservationDal = iReservationDal;
    }

    @Override
    public Room getRoomById(Long id) {
        return iRoomDal.getRoomById(id);
    }

    @Override
    public List<Room> getAllRooms() {
        return iRoomDal.getAllRooms();
    }

    @Override
    public List<Room> getRoomsOfHotel(Long hotelId) {
        return iRoomDal.getRoomsOfHotel(hotelId);
    }

    @Override
    public Room addRoomToHotel(Room room) {
         return iRoomDal.addRoomToHotel(room);
    }


    @Override
    public List<Room> getAvailableRoomsOfHotel(Date checkIn, Date checkOut, Long hotelId) {
        List<Room> rooms = iRoomDal.getRoomsOfHotel(hotelId);
        List<Reservation> reservations = iReservationDal.getReservationsOfHotel(hotelId);
        if(!reservations.isEmpty())
        {
            for(Reservation reservation : iReservationDal.getReservationsOfHotel(hotelId))
            {
                if(!checkDates(reservation.getCheckIn(), reservation.getCheckOut(), checkIn, checkOut))
                {
                    rooms.remove(iRoomDal.getRoomById(reservation.getRoomId().getId()));
                }
            }
        }

        return rooms;
    }

    private boolean checkDates(Date reservationDate1, Date reservationDate2, Date checkIn, Date checkOut)
    {
        if(checkIn.before(reservationDate1) && checkOut.before(reservationDate1))
        {
            return true;
        }
        else if(checkIn.after(reservationDate2) && checkOut.after(reservationDate2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
