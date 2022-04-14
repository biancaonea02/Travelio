package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.serviceInterfaces.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    public HotelController(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable(value = "id") Long id) {
        Hotel hotel = hotelService.getHotelById(id);

        if (hotel != null) {
            return new ResponseEntity<>(hotel, OK);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return new ResponseEntity<>(hotelService.getAllHotels(), OK);
    }

    @GetMapping("/filterByStars")
    public ResponseEntity<List<Hotel>> getHotelByNrStars(@PathParam("rating") int rating) {
        List<Hotel> filtered = new ArrayList<>();
        if(rating != 0)
        {
            List<Hotel> filtered2 = hotelService.getHotelsByRating(rating);
            for (Hotel hotel : filtered2) {
                filtered.add(hotel);
            }
        }
        if (filtered != null) {
            return ResponseEntity.ok().body(filtered);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filterByCity")
    public ResponseEntity<List<Hotel>> getHotelsOfCity(@PathParam(value = "city") String city) {
        List<Hotel> hotels = hotelService.getHotelsOfCity(city);

        if(hotels != null)
        {
            return ResponseEntity.ok().body(hotels);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/numberOfHotelsOfCity")
    public ResponseEntity<Integer> getNumberOfHotelsOfCity(@PathParam(value = "city") String city) {
        List<Hotel> hotels = hotelService.getHotelsOfCity(city);
        return new ResponseEntity<>(hotels.size(), OK);
    }

    @PostMapping("/addHotel")
    public Hotel addNewHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    @GetMapping("/hotelsOfOwner/{userId}")
    public ResponseEntity<List<Hotel>> getHotelsOfOwner(@PathVariable(value = "userId") Long userId) {
        return new ResponseEntity<>(hotelService.getHotelsOfOwner(userId), OK);
    }

    @GetMapping("/lastFive")
    public ResponseEntity<List<Hotel>> getLastFiveAddedHotels() {
        return new ResponseEntity<>(hotelService.getLastFiveAddedHotels(), OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Hotel> updateHotel(@RequestParam(value = "hotelId") Long hotelId,
                                             @RequestParam(value = "name") String name,
                                             @RequestParam(value = "rating") int rating,
                                             @RequestParam(value = "phoneNumber") String phoneNumber,
                                             @RequestParam(value = "freeParking") boolean freeParking,
                                             @RequestParam(value = "freeCancellation") boolean freeCancellation,
                                             @RequestParam(value = "description") String description,
                                             @RequestParam(value = "facilities") String facilities) {

        return new ResponseEntity<>(hotelService.updateHotel(hotelId, name, rating, phoneNumber, freeParking,
                freeCancellation, description, facilities), OK);
    }
}
