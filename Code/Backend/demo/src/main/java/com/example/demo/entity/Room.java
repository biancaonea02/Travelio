package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotelId;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "pricePerNight")
    private double pricePerNight;

    @Column(name = "type")
    private String type;

    @Column(name = "reserved")
    private Boolean reserved;

    @Column(name = "dimension")
    private int dimension;

    @Column(name = "facilities")
    private String facilities;

    public Room(Long id, Hotel hotelId, int capacity, double pricePerNight, String type, Boolean reserved, int dimension, String facilities) {
        this.id = id;
        this.hotelId = hotelId;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.type = type;
        this.reserved = reserved;
        this.dimension = dimension;
        this.facilities = facilities;
    }

    public Room() {
    }

    public Room(Long id, Hotel hotelId) {
        this.id = id;
        this.hotelId = hotelId;
    }

    //Getters and Setters
    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

    public Hotel getHotelId() {return hotelId;}
    public void setHotelId(Hotel hotelId) {this.hotelId = hotelId;}

    public int getCapacity() {return this.capacity;}
    public void setCapacity(int capacity) {this.capacity = capacity;}

    public double getPricePerNight() {return this.pricePerNight;}
    public void setPricePerNight(double pricePerNight) {this.pricePerNight = pricePerNight;}

    public String getType() {return this.type;}
    public void setType(String type) {this.type = type;}

    public String getFacilities() {return facilities;}
    public void setFacilities(String facilities) {this.facilities = facilities;}

    public int getDimension() {return dimension;}
    public void setDimension(int dimension) {this.dimension = dimension;}

    public Boolean getReserved() {return reserved;}
    public void setReserved(Boolean reserved) {this.reserved = reserved;}
}
