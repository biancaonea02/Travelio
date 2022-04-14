package com.example.demo.dal;

import com.example.demo.entity.Hotel;

import java.util.List;

public interface IHotelDal {
    Hotel getHotelById(Long id);
    List<Hotel> getAllHotels();
    Hotel addHotel(Hotel h);
    List<Hotel> getHotelsOfOwner(Long userId);
}
