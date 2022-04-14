package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "favouritehotels")
public class FavouriteHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotelId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    public FavouriteHotel(Long id, Hotel hotelId, User userId) {
        this.id = id;
        this.hotelId = hotelId;
        this.userId = userId;
    }

    public FavouriteHotel() {
    }


    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Hotel getHotelId() {return hotelId;}
    public void setHotelId(Hotel hotelId) {this.hotelId = hotelId;}

    public User getUserId() {return userId;}
    public void setUserId(User userId) {this.userId = userId;}
}
