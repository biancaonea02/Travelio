import React, { useState, useEffect } from 'react'
import ReservationService from '../../services/ReservationService'
import ReservationCard from './ReservationCard'
import './ManageReservations.css'
function ManageReservations() {

    const [userReservations, setUserReservations] = useState([])

    useEffect(() => {
        ReservationService.getReservationOfUser(setUserReservations);
    }, [])


    return (
        <div className="all-reservations-of-user">
            <h1 className="reservations-heading">My Reservations</h1>
            <div className="user-reservations">
                {userReservations.map(reservation => (
                    <ReservationCard hotelName={reservation.hotelId.name} checkIn={reservation.checkIn} checkOut={reservation.checkOut}
                        typeOfRoom={reservation.roomId.type} pricePerNight={reservation.roomId.pricePerNight}
                        nrAdults={reservation.nrAdults} nrChildren={reservation.nrChildren} freeCancellation={reservation.hotelId.freeCancellation}
                        id={reservation.id} />
                )
                )}
            </div>
        </div>
    )
}

export default ManageReservations
