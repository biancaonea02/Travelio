package com.example.demo.serviceInterfaces;

import com.example.demo.entity.Room;

import java.util.Date;
import java.util.List;

public interface IRoomService {
    Room getRoomById(Long id);
    List<Room> getAllRooms();
    List<Room> getRoomsOfHotel(Long hotelId);
    Room addRoomToHotel(Room room);
    List<Room> getAvailableRoomsOfHotel(Date checkIn, Date checkOut, Long hotelId);
}
