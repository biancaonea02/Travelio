package com.example.demo.dal.implementation;

import com.example.demo.dal.IFavouriteHotelDal;
import com.example.demo.dao.FavouriteHotelDao;
import com.example.demo.entity.FavouriteHotel;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavouriteHotelDalJPA implements IFavouriteHotelDal {

    @Autowired
    FavouriteHotelDao favouriteHotelRepository;

    @Override
    public FavouriteHotel getFavouriteHotelById(Long id) {
        return favouriteHotelRepository.getById(id);
    }

    @Override
    public List<FavouriteHotel> getAllFavouriteHotels() {
        return favouriteHotelRepository.findAll();
    }

    @Override
    public FavouriteHotel addHotelToFavourites(FavouriteHotel favouriteHotel) {
        return favouriteHotelRepository.save(favouriteHotel);
    }

    @Override
    public FavouriteHotel getFavouriteHotelOfUser(Long userId, Long hotelId) {
        for(FavouriteHotel favouriteHotel : favouriteHotelRepository.findAll())
        {
            if(favouriteHotel.getHotelId().getId().equals(hotelId) && favouriteHotel.getUserId().getId().equals(userId))
            {
                return favouriteHotel;
            }
        }
        return null;
    }

    @Override
    public void removeHotelFromFavourites(FavouriteHotel favouriteHotel) {
        favouriteHotelRepository.delete(favouriteHotel);
    }

}
