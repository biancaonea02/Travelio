package com.example.demo.dal.implementation;

import com.example.demo.dal.IHotelApplicationDal;
import com.example.demo.dao.FavouriteHotelDao;
import com.example.demo.dao.HotelApplicationDao;
import com.example.demo.entity.HotelApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HotelApplicationDalJPA implements IHotelApplicationDal {

    @Autowired
    HotelApplicationDao hotelApplicationRepository;


    @Override
    public HotelApplication getHotelApplicationById(Long id) {
        return hotelApplicationRepository.findById(id).get();
    }

    @Override
    public List<HotelApplication> getAllHotelApplications() {
        return hotelApplicationRepository.findAll();
    }

    @Override
    public HotelApplication addHotelApplication(HotelApplication hotelApplication) {
        return hotelApplicationRepository.save(hotelApplication);
    }

    @Override
    public void acceptApplication(Long id) {
        HotelApplication hotelApplication = hotelApplicationRepository.getById(id);
        hotelApplication.setStatus("ACCEPTED");
        hotelApplicationRepository.save(hotelApplication);
    }

    @Override
    public void declineApplication(Long id) {
        HotelApplication hotelApplication = hotelApplicationRepository.getById(id);
        hotelApplication.setStatus("DECLINED");
        hotelApplicationRepository.save(hotelApplication);
    }

    @Override
    public List<HotelApplication> getHotelApplicationsOfUser(Long userId) {
        List<HotelApplication> applications = new ArrayList<>();
        for(HotelApplication hotelApplication : hotelApplicationRepository.findAll())
        {
            if(hotelApplication.getUserId().getId().equals(userId))
            {
                applications.add(hotelApplication);
            }
        }
        return applications;
    }

    @Override
    public List<HotelApplication> getHotelApplicationsOfUserByStatus(Long userId, String status) {
        List<HotelApplication> applications = new ArrayList<>();
        for(HotelApplication hotelApplication : hotelApplicationRepository.findAll())
        {
            if(hotelApplication.getUserId().getId().equals(userId) && hotelApplication.getStatus().equals(status))
            {
                applications.add(hotelApplication);
            }
        }
        return applications;
    }

    @Override
    public List<HotelApplication> getHotelApplicationsByStatus(String status) {
        List<HotelApplication> applications = new ArrayList<>();
        for(HotelApplication hotelApplication : hotelApplicationRepository.findAll())
        {
            if(hotelApplication.getStatus().equals(status))
            {
                applications.add(hotelApplication);
            }
        }
        return applications;
    }
}
