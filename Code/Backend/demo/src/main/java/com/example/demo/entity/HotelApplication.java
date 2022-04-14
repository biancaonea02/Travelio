package com.example.demo.entity;


import javax.persistence.*;

@Entity
@Table(name ="hotelApplications")
public class HotelApplication {

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
    @JoinColumn(name = "userId")
    private User userId;

    @Column(name = "status")
    private String status;

    public HotelApplication(Long id, String name, String city, String country, String address, int rating, String phoneNumber, Boolean freeParking, Boolean freeCancellation, Boolean topLocation, String description, String facilities, User userId, String status) {
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
        this.userId = userId;
        this.status = status;
    }

    public HotelApplication() {
    }

    public HotelApplication(Long id, User userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public int getRating() {return rating;}
    public void setRating(int rating) {this.rating = rating;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

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

    public User getUserId() {return userId;}
    public void setUserId(User userId) {this.userId = userId;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
}
