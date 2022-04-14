package com.example.demo.entity;


import javax.persistence.*;

@Entity
@Table(name ="hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "rating")
    private int rating;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "freeParking")
    private Boolean freeParking;

    @Column(name = "freeCancellation")
    private Boolean freeCancellation;

    @Column(name = "topLocation")
    private Boolean topLocation;

    @Column(name = "description")
    private String description;

    @Column(name = "facilities")
    private String facilities;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    public Hotel(Long id, String name, String city, String country, String address, int rating, String phoneNumber,
                 Boolean freeParking, Boolean freeCancellation, Boolean topLocation, String description, String facilities, User owner) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.address = address;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.freeParking = freeParking;
        this.freeCancellation = freeCancellation;
        this.topLocation = topLocation;
        this.description = description;
        this.facilities = facilities;
        this.owner = owner;
    }

    public Hotel(String name, String city, String country, String address, int rating, String phoneNumber,
                 Boolean freeParking, Boolean freeCancellation, Boolean topLocation, String description, String facilities, User owner) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.address = address;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.freeParking = freeParking;
        this.freeCancellation = freeCancellation;
        this.topLocation = topLocation;
        this.description = description;
        this.facilities = facilities;
        this.owner = owner;
    }

    public Hotel() {
        ;
    }

    public Hotel(Long id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    //Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id;}

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {return this.country;}
    public void setCountry(String country) {this.country = country;}

    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getRating() {return this.rating;}
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getFreeParking() {return freeParking;}
    public void setFreeParking(Boolean freeParking) {this.freeParking = freeParking;}

    public Boolean getFreeCancellation() {return freeCancellation;}
    public void setFreeCancellation(Boolean freeCancellation) {this.freeCancellation = freeCancellation;}

    public Boolean getTopLocation() {return topLocation;}
    public void setTopLocation(Boolean topLocation) {this.topLocation = topLocation;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getFacilities() {return facilities;}
    public void setFacilities(String facilities) {this.facilities = facilities;}

    public User getOwner() {return owner;}
    public void setOwner(User owner) {this.owner = owner;}
}
