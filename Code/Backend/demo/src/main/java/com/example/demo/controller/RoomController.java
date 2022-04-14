package com.example.demo.controller;

import com.example.demo.entity.Room;
import com.example.demo.serviceInterfaces.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @GetMapping("/getHotelRooms")
    public ResponseEntity<List<Room>> getRoomsOfHotel(@PathParam("idHotel") Long idHotel) {
        if (idHotel != 0) {
            return ResponseEntity.ok().body(roomService.getRoomsOfHotel(idHotel));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = null;
        rooms = roomService.getAllRooms();

        if(rooms != null)
        {
            return ResponseEntity.ok().body(rooms);
        }
        else
        {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") Long id)
    {
        Room room = roomService.getRoomById(id);
        return new ResponseEntity<>(room, OK);
    }


    @GetMapping("/getAvailableHotelRooms")
    public ResponseEntity<List<Room>> getAvailableHotelRooms(@PathParam("checkIn")  String checkIn,
                                                             @PathParam("checkOut") String checkOut,
                                                             @PathParam("idHotel")  Long idHotel) throws ParseException {

        Date checkInDate = new SimpleDateFormat("yyyy/MM/dd").parse(checkIn);
        Date checkOutDate = new SimpleDateFormat("yyyy/MM/dd").parse(checkOut);

        if (idHotel != 0) {
            return ResponseEntity.ok().body(roomService.getAvailableRoomsOfHotel(checkInDate, checkOutDate, idHotel));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getNrAvailableHotelRooms")
    public ResponseEntity<Integer> getNrOfAvailableHotelRooms(@PathParam("checkIn")  String checkIn,
                                                              @PathParam("checkOut") String checkOut,
                                                              @PathParam("idHotel")  Long idHotel) throws ParseException {

        Date checkInDate = new SimpleDateFormat("yyyy/MM/dd").parse(checkIn);
        Date checkOutDate = new SimpleDateFormat("yyyy/MM/dd").parse(checkOut);

        if (idHotel != 0) {
            return ResponseEntity.ok().body(roomService.getAvailableRoomsOfHotel(checkInDate, checkOutDate, idHotel).size());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addRoom")
    public void addRoomToHotel(@RequestBody Room room) {
        roomService.addRoomToHotel(room);
    }


}
