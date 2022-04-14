import React, { useState, useEffect } from 'react'
import ReservationService from '../../../../../services/ReservationService'
import '../HotelStatistics/HotelStatistics.css'
import TableRowReservation from './TableRowReservation'

function ReservationStatistics() {

    const [lastReservations, setLastReservations] = useState([])

    useEffect(() => {
        ReservationService.getLastFiveReservations(setLastReservations);
    }, [])


    return (
        <div className="reservations-statistics-wrapper">
            <div className="panel-reservations-statistics">
                <div className="panel-header">
                    <h3 className="title">Last reservations made through the website</h3>
                </div>

                <div className="panel-body">
                    <div className="user-statistics-container">
                        <div className="table">
                            <div className="table-header">
                                <div className="header__item"><a id="name" className="filter__link">Hotel</a></div>
                                <div className="header__item"><a id="wins" className="filter__link filter__link--number">Check In</a></div>
                                <div className="header__item"><a id="draws" className="filter__link filter__link--number">Check Out</a></div>
                                <div className="header__item"><a id="losses" className="filter__link filter__link--number">User's E-mail</a></div>
                            </div>
                            <div className="table-content">
                                {lastReservations.map(res => (
                                    <TableRowReservation hotelName={res.hotelId.name} checkIn={res.checkIn} checkOut={res.checkOut} userEmail={res.userId.email}
                                        statistics={false} />
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

export default ReservationStatistics
