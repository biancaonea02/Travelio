package com.example.demo.dao;

import com.example.demo.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelDao extends JpaRepository<Hotel, Long> {
}