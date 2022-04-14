package com.example.demo.dal;

import com.example.demo.entity.HotelApplication;

import java.util.List;

public interface IHotelApplicationDal {
    HotelApplication getHotelApplicationById(Long id);
    List<HotelApplication> getAllHotelApplications();
    HotelApplication addHotelApplication(HotelApplication hotelApplication);
    void acceptApplication(Long id);
    void declineApplication(Long id);
    List<HotelApplication> getHotelApplicationsOfUser(Long userId);
    List<HotelApplication> getHotelApplicationsOfUserByStatus(Long userId, String status);
    List<HotelApplication> getHotelApplicationsByStatus(String status);


}
