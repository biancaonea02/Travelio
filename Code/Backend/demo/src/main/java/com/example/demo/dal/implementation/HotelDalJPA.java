package com.example.demo.dal.implementation;

import com.example.demo.dal.IHotelDal;
import com.example.demo.dao.HotelDao;
import com.example.demo.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class HotelDalJPA implements IHotelDal {
    @Autowired
    HotelDao hotelDao;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDao.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelDao.findById(id).get();
    }

    @Override
    public Hotel addHotel(Hotel h) {

        hotelDao.save(h);
        return h;
    }

    @Override
    public List<Hotel> getHotelsOfOwner(Long userId) {
        List<Hotel> hotelsOfOwner = new ArrayList<>();
        for(Hotel hotel : hotelDao.findAll())
        {
            if(hotel.getOwner().getId().equals(userId))
            {
                hotelsOfOwner.add(hotel);
            }
        }
        return hotelsOfOwner;
    }
}
