package com.example.demo.serviceInterfaces;

import com.example.demo.entity.Hotel;

import java.util.List;

public interface IHotelService {
    Hotel getHotelById(Long id);
    List<Hotel> getAllHotels();
    Hotel addHotel(Hotel h);
    List<Hotel> getHotelsOfCity(String city);
    List<Hotel> getHotelsByRating(int rating);
    List<Hotel> getHotelsOfOwner(Long userId);
    List<Hotel> getLastFiveAddedHotels();
    Hotel updateHotel(Long hotelId, String name, int rating, String phoneNumber, boolean freeParking, boolean freeCancellation,
                      String description, String facilities);
}
