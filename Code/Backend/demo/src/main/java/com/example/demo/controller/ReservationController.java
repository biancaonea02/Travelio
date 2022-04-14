package com.example.demo.controller;


import com.example.demo.entity.Reservation;
import com.example.demo.serviceInterfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private IReservationService iReservationService;

    public ReservationController(IReservationService iReservationService)
    {
        this.iReservationService = iReservationService;
    }

    @GetMapping("/getHotelReservation")
    public ResponseEntity<List<Reservation>> getReservationsOfHotel(@PathParam("idHotel") Long idHotel) {
        if (idHotel != 0) {
            return ResponseEntity.ok().body(iReservationService.getReservationsOfHotel(idHotel));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getUserReservation")
    public ResponseEntity<List<Reservation>> getReservationsOfUser(@PathParam("idUser") Long idUser) {
        return new ResponseEntity<>(iReservationService.getReservationsOfUser(idUser), OK);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = iReservationService.getAllReservations();

        if(reservations != null)
        {
            return ResponseEntity.ok().body(reservations);
        }
        else
        {
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addReservation")
    public void addReservation(@RequestBody Reservation reservation) {
        iReservationService.addReservation(reservation);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id")Long id)
    {
        iReservationService.deleteReservation(id);
        return ResponseEntity.ok().body("The reservation was successfully cancelled");
    }

    @GetMapping("/getLastReservations")
    public ResponseEntity<List<Reservation>> getLastFiveReservations() {
        return new ResponseEntity<>(iReservationService.getLastFiveReservations(), OK);
    }

    @GetMapping("/getReservationsOfHotelOwner/{userId}")
    public ResponseEntity<List<Reservation>> getLastFiveReservations(@PathVariable("userId")Long userId) {
        return new ResponseEntity<>(iReservationService.getReservationsOfHotelOwner(userId), OK);
    }


}
