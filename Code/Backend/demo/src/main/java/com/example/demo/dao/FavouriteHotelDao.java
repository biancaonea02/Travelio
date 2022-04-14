package com.example.demo.dao;

import com.example.demo.entity.FavouriteHotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteHotelDao extends JpaRepository<FavouriteHotel, Long> {
}
