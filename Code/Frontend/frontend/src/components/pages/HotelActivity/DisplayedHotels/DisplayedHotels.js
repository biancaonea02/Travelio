import React, { useState, useEffect } from 'react'
import DisplayedHotelCard from './DisplayedHotelCard';
import HotelService from '../../../../services/HotelService';

function DisplayedHotels() {

    const [displayedHotels, setDisplayedHotels] = useState([]);

    useEffect(() => {
        HotelService.getHotelsOfOwner(setDisplayedHotels);
    }, [])

    return (
        <div className="displayed-hotels">
            <h1 className="displayed-hotels-heading">Your Displayed Hotels</h1>
            <div className="displayed-hotels-cards">
                {displayedHotels.map(hotel => (
                    <DisplayedHotelCard hotelName={hotel.name} country={hotel.country} city={hotel.city}
                        address={hotel.address} id={hotel.id} />
                )
                )}
            </div>
        </div>
    )
}

export default DisplayedHotels
