package com.example.demo.dao;

import com.example.demo.entity.HotelApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelApplicationDao extends JpaRepository<HotelApplication, Long> {
}
