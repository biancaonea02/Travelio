package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.serviceInterfaces.IFavouriteHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/favouriteHotel")
public class FavouriteHotelController {

    @Autowired
    private IFavouriteHotelService favouriteHotelService;

    public FavouriteHotelController(IFavouriteHotelService favouriteHotelService) {
        this.favouriteHotelService = favouriteHotelService;
    }

    @GetMapping("{id}")
    public ResponseEntity<FavouriteHotel> getFavouriteHotelById(@PathVariable(value = "id") Long id) {
        FavouriteHotel favouriteHotel = favouriteHotelService.getFavouriteHotelById(id);

        if (favouriteHotel != null) {
            return new ResponseEntity<>(favouriteHotel, OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FavouriteHotel>> getAllHotels() {
        List<FavouriteHotel> favouriteHotels = favouriteHotelService.getAllFavouriteHotels();
        return new ResponseEntity<>(favouriteHotels, OK);
    }

    @GetMapping("/likedByUser")
    public ResponseEntity<List<Hotel>> getHotelsLikedByUser(@PathParam(value = "idUser") Long idUser) {
        List<Hotel> favouriteHotels = favouriteHotelService.getHotelsLikedByUser(idUser);
        return new ResponseEntity<>(favouriteHotels, OK);
    }

    @GetMapping("/usersOfHotel")
    public ResponseEntity<List<User>> getUsersWhoLikeTheHotel(@PathParam(value = "idHotel") Long idHotel) {
        List<User> users = favouriteHotelService.getUsersWhoLikeTheHotel(idHotel);
        return new ResponseEntity<>(users, OK);
    }

    @PostMapping("/addHotelToFavourites")
    public FavouriteHotel addHotelToFavourites(@RequestBody FavouriteHotel favouriteHotel) {
        return favouriteHotelService.addHotelToFavourites(favouriteHotel);
    }

    @GetMapping("/isHotelSaved")
    public ResponseEntity<Boolean> isHotelSavedToFavourites(@PathParam(value = "idUser") Long idUser,
                                                            @PathParam(value = "idHotel") Long idHotel) {
        return new ResponseEntity<>(favouriteHotelService.isHotelSavedToFavourites(idUser, idHotel), OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> removeHotelFromFavourites(@PathParam(value = "userId") Long userId,
                                                             @PathParam(value = "hotelId") Long hotelId)
    {
        favouriteHotelService.removeHotelFromFavourites(userId, hotelId);
        return new ResponseEntity<>(true, OK);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message)
    {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
