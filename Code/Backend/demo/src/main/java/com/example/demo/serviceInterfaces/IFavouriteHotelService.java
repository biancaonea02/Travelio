package com.example.demo.serviceInterfaces;

import com.example.demo.entity.FavouriteHotel;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.User;

import java.util.List;

public interface IFavouriteHotelService {
    FavouriteHotel getFavouriteHotelById(Long id);
    List<FavouriteHotel> getAllFavouriteHotels();
    FavouriteHotel addHotelToFavourites(FavouriteHotel favouriteHotel);
    void removeHotelFromFavourites(Long userId, Long hotelId);
    List<Hotel> getHotelsLikedByUser(Long userId);
    List<User> getUsersWhoLikeTheHotel(Long hotelId);
    Boolean isHotelSavedToFavourites(Long userId, Long hotelId);
}
