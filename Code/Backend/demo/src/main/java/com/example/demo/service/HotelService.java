package com.example.demo.service;

import com.example.demo.dal.IHotelDal;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.User;
import com.example.demo.serviceInterfaces.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class HotelService implements IHotelService {
    @Autowired
    private IHotelDal dal;

    @Autowired
    public HotelService(IHotelDal dal)
    {
        this.dal = dal;
    }


    @Override
    public Hotel getHotelById(Long id) {
        return dal.getHotelById(id);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return dal.getAllHotels();
    }

    @Override
    public Hotel addHotel(Hotel h) {
        dal.addHotel(h);
        return h;
    }

    @Override
    public List<Hotel> getHotelsOfCity(String city) {
        List<Hotel> hotels = new ArrayList<>();
        for(Hotel h : dal.getAllHotels())
        {
            if(h.getCity().equals(city))
            {
                hotels.add(h);
            }
        }
        return hotels;
    }

    @Override
    public List<Hotel> getHotelsByRating(int rating) {
        List<Hotel> hotels = new ArrayList<>();
        for(Hotel h : dal.getAllHotels())
        {
            if(h.getRating() == rating)
            {
                hotels.add(h);
            }
        }
        return hotels;
    }

    @Override
    public List<Hotel> getHotelsOfOwner(Long userId) {
        return dal.getHotelsOfOwner(userId);
    }

    @Override
    public List<Hotel> getLastFiveAddedHotels() {
        List<Hotel> lastFive = new ArrayList<>();
        int i;
        for(i = dal.getAllHotels().size() - 1; i >= dal.getAllHotels().size() - 5; i--)
        {
            lastFive.add(dal.getAllHotels().get(i));
        }
        return lastFive;
    }

    @Override
    public Hotel updateHotel(Long hotelId, String name, int rating, String phoneNumber, boolean freeParking, boolean freeCancellation, String description, String facilities) {
        Hotel hotelToUpdate = dal.getHotelById(hotelId);
        if(hotelToUpdate != null)
        {
            hotelToUpdate.setName(name);
            hotelToUpdate.setRating(rating);
            hotelToUpdate.setPhoneNumber(phoneNumber);
            hotelToUpdate.setFreeParking(freeParking);
            hotelToUpdate.setFreeCancellation(freeCancellation);
            hotelToUpdate.setDescription(description);
            hotelToUpdate.setFacilities(facilities);
            dal.addHotel(hotelToUpdate);
            return hotelToUpdate;
        }
        return null;
    }
}
