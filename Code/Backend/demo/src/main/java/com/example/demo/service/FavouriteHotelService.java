package com.example.demo.service;

import com.example.demo.dal.IFavouriteHotelDal;
import com.example.demo.entity.FavouriteHotel;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.User;
import com.example.demo.serviceInterfaces.IFavouriteHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteHotelService implements IFavouriteHotelService {

    @Autowired
    IFavouriteHotelDal favouriteHotelDal;

    public FavouriteHotelService(IFavouriteHotelDal favouriteHotelDal) {this.favouriteHotelDal = favouriteHotelDal;}


    @Override
    public FavouriteHotel getFavouriteHotelById(Long id) {
        return favouriteHotelDal.getFavouriteHotelById(id);
    }

    @Override
    public List<FavouriteHotel> getAllFavouriteHotels() {
        return favouriteHotelDal.getAllFavouriteHotels();
    }

    @Override
    public FavouriteHotel addHotelToFavourites(FavouriteHotel favouriteHotel) {
        return favouriteHotelDal.addHotelToFavourites(favouriteHotel);
    }

    @Override
    public void removeHotelFromFavourites(Long userId, Long hotelId) {
        FavouriteHotel favouriteHotel = favouriteHotelDal.getFavouriteHotelOfUser(userId, hotelId);
        favouriteHotelDal.removeHotelFromFavourites(favouriteHotel);
    }

    @Override
    public List<Hotel> getHotelsLikedByUser(Long userId) {
        List<Hotel> hotels = new ArrayList<>();
        for(FavouriteHotel favouriteHotel : favouriteHotelDal.getAllFavouriteHotels())
        {
            if(favouriteHotel.getUserId().getId().equals(userId))
            {
                hotels.add(favouriteHotel.getHotelId());
            }
        }
        return hotels;
    }

    @Override
    public List<User> getUsersWhoLikeTheHotel(Long hotelId) {
        List<User> users = new ArrayList<>();
        for(FavouriteHotel favouriteHotel : favouriteHotelDal.getAllFavouriteHotels())
        {
            if(favouriteHotel.getHotelId().getId().equals(hotelId))
            {
                users.add(favouriteHotel.getUserId());
            }
        }
        return users;
    }

    @Override
    public Boolean isHotelSavedToFavourites(Long userId, Long hotelId) {
        for(Hotel hotel : this.getHotelsLikedByUser(userId))
        {
            if(hotel.getId().equals(hotelId))
            {
                return true;
            }
        }
        return false;
    }
}
