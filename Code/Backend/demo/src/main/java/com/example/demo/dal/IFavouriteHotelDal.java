package com.example.demo.dal;

import com.example.demo.entity.FavouriteHotel;

import java.util.List;

public interface IFavouriteHotelDal {
    FavouriteHotel getFavouriteHotelById(Long id);
    List<FavouriteHotel> getAllFavouriteHotels();
    FavouriteHotel addHotelToFavourites(FavouriteHotel favouriteHotel);
    FavouriteHotel getFavouriteHotelOfUser(Long userId, Long hotelId);
    void removeHotelFromFavourites(FavouriteHotel favouriteHotel);
}
