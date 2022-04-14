package com.example.demo.service;


import com.example.demo.dal.IHotelApplicationDal;
import com.example.demo.entity.HotelApplication;
import com.example.demo.serviceInterfaces.IHotelApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelApplicationService implements IHotelApplicationService {

    @Autowired
    IHotelApplicationDal hotelApplicationDal;

    public HotelApplicationService(IHotelApplicationDal hotelApplicationDal) {
        this.hotelApplicationDal = hotelApplicationDal;
    }

    @Override
    public HotelApplication getHotelApplicationById(Long id) {
        return hotelApplicationDal.getHotelApplicationById(id);
    }

    @Override
    public List<HotelApplication> getAllHotelApplications() {
        return hotelApplicationDal.getAllHotelApplications();
    }

    @Override
    public HotelApplication addHotelApplication(HotelApplication hotelApplication) {
        return hotelApplicationDal.addHotelApplication(hotelApplication);
    }

    @Override
    public void acceptApplication(Long id) {
        hotelApplicationDal.acceptApplication(id);
    }

    @Override
    public void declineApplication(Long id) {
        hotelApplicationDal.declineApplication(id);
    }

    @Override
    public List<HotelApplication> getHotelApplicationsOfUser(Long userId) {
        return hotelApplicationDal.getHotelApplicationsOfUser(userId);
    }

    @Override
    public List<HotelApplication> getHotelApplicationsOfUserByStatus(Long userId, String status) {
        return hotelApplicationDal.getHotelApplicationsOfUserByStatus(userId, status);
    }

    @Override
    public List<HotelApplication> getHotelApplicationsByStatus(String status) {
        return hotelApplicationDal.getHotelApplicationsByStatus(status);
    }

//    @Override
//    public List<HotelApplication> getPendingApplications() {
//        return hotelApplicationDal.getPendingApplications();
//    }

}
