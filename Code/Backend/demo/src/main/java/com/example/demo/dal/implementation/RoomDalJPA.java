package com.example.demo.dal.implementation;

import com.example.demo.dal.IRoomDal;
import com.example.demo.dao.RoomDao;
import com.example.demo.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomDalJPA implements IRoomDal {

    @Autowired
    RoomDao roomDao;

    @Override
    public Room getRoomById(Long id) {
        return roomDao.findById(id).get();
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDao.findAll();
    }

    @Override
    public List<Room> getRoomsOfHotel(Long hotelId) {
        List<Room> rooms = new ArrayList<>();
        for(Room room : roomDao.findAll())
        {
            if(room.getHotelId().getId() == hotelId)
            {
                rooms.add(room);
            }
        }
        return rooms;
    }

    @Override
    public Room addRoomToHotel(Room room) {
        return roomDao.save(room);
    }
}
