import React, { useState, useEffect } from 'react'
import AuthenticationService from '../../../../services/AuthenticationService';
import ReservationService from '../../../../services/ReservationService';
import TableRowReservation from '../../AdminView/Dashboard/ReservationStatistics/TableRowReservation'

function HotelReservations() {
    const [reservations, setReservations] = useState([])

    useEffect(() => {
        ReservationService.getReservationOfHotelOwner(setReservations, AuthenticationService.getCurrentUser().id);
    }, [])


    return (
        <div className="reservations-statistics-wrapper">
            <div className="panel-reservations-statistics">
                <div className="panel-header">
                    <h3 className="title">Reservations For Your Hotels</h3>
                </div>

                <div className="panel-body">
                    <div className="user-statistics-container">
                        <div className="table">
                            <div className="table-header">
                                <div className="header__item"><a id="name" className="filter__link">Hotel</a></div>
                                <div className="header__item"><a id="wins" className="filter__link filter__link--number">Check In</a></div>
                                <div className="header__item"><a id="draws" className="filter__link filter__link--number">Check Out</a></div>
                                <div className="header__item"><a id="losses" className="filter__link filter__link--number">Person's E-mail</a></div>
                                <div className="header__item"><a id="draws" className="filter__link filter__link--number">Room Type</a></div>
                                <div className="header__item"><a id="losses" className="filter__link filter__link--number">Price Per Night</a></div>
                            </div>
                            <div className="table-content">
                                {reservations.map(res => (
                                    <TableRowReservation hotelName={res.hotelId.name} checkIn={res.checkIn} checkOut={res.checkOut} userEmail={res.userId.email}
                                        statistics={true} typeOfRoom={res.roomId.type} pricePerNight={res.roomId.pricePerNight} />
                                )
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default HotelReservations
