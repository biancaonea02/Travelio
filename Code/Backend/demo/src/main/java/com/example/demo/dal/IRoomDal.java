package com.example.demo.dal;

import com.example.demo.entity.Room;

import java.util.List;

public interface IRoomDal {
    Room getRoomById(Long id);
    List<Room> getAllRooms();
    List<Room> getRoomsOfHotel(Long hotelId);
    Room addRoomToHotel(Room room);
}
