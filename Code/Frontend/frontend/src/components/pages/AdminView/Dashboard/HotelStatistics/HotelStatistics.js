import React, { useState, useEffect } from 'react'
import './HotelStatistics.css'
import HotelService from '../../../../../services/HotelService'
import TableRowHotel from './TableRowHotel';

function HotelStatistics(props) {

    const [hotels, setHotels] = useState([])
    const [lastHotels, setLastHotels] = useState([]);
    let cities = [];
    useEffect(() => {
        HotelService.getAllHotels(setHotels);
        HotelService.getLastFiveHotels(setLastHotels);
    }, [])


    const unique = (value, index, self) => {
        return self.indexOf(value) === index;
    }

    for (const hotel of hotels) {
        cities.push(hotel.city);
    }

    cities = cities.filter(unique);


    return (
        <div className="hotel-charts">
            <div className="hotel-statistics-wrapper">
                <div className="panel">
                    <div className="panel-header">
                        <h3 className="title">Hotels Statistics - Last hotels that joined the website</h3>
                    </div>
                    <div className="panel-body">
                        <div className="categories">
                            <div className="category">
                                <span>Total Hotels</span>
                                <span>{hotels.length}</span>
                            </div>
                            <div className="category">
                                <span>Cities</span>
                                <span>{cities.length}</span>
                            </div>
                            <div className="category">
                                <span>Reservations</span>
                                <span>{props.reservations}</span>
                            </div>
                        </div>
                        <div className="hotel-statistics-container">
                            <div className="table">
                                <div className="table-header-hotels">
                                    <div className="header__item"><a id="name" className="filter__link">Hotel Name</a></div>
                                    <div className="header__item"><a id="wins" className="filter__link filter__link--number">City</a></div>
                                    <div className="header__item"><a id="draws" className="filter__link filter__link--number">Country</a></div>
                                </div>
                                <div className="table-content">
                                    {lastHotels.map(h => (
                                        <TableRowHotel name={h.name} city={h.city} country={h.country} />
                                    )
                                    )}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default HotelStatistics
