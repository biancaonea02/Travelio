package com.example.demo.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {

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

    @Column(name = "checkIn")
    private Date checkIn;

    @Column(name = "checkOut")
    private Date checkOut;

    @Column(name = "nrAdults")
    private int nrAdults;

    @Column(name = "nrChildren")
    private int nrChildren;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room roomId;

    public Reservation(Long id, Hotel hotelId, User userId, Date checkIn, Date checkOut, int nrAdults, int nrChildren, Room roomId) {
        this.id = id;
        this.hotelId = hotelId;
        this.userId = userId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.nrAdults = nrAdults;
        this.nrChildren = nrChildren;
        this.roomId = roomId;
    }

    public Reservation() {
    }

    public Reservation(Long id, Hotel hotelId, User userId) {
        this.id = id;
        this.hotelId = hotelId;
        this.userId = userId;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Hotel getHotelId() {return hotelId;}
    public void setHotelId(Hotel hotelId) {this.hotelId = hotelId;}

    public User getUserId() {return userId;}
    public void setUserId(User userId) {this.userId = userId;}

    public Date getCheckIn() {return checkIn;}
    public void setCheckIn(Date checkIn) {this.checkIn = checkIn;}

    public Date getCheckOut() {return checkOut;}
    public void setCheckOut(Date checkOut) {this.checkOut = checkOut;}

    public int getNrAdults() {return nrAdults;}
    public void setNrAdults(int nrAdults) {this.nrAdults = nrAdults;}

    public int getNrChildren() {return nrChildren;}
    public void setNrChildren(int nrChildren) {this.nrChildren = nrChildren;}

    public Room getRoomId() {return roomId;}
    public void setRoomId(Room roomId) {this.roomId = roomId;}
}
