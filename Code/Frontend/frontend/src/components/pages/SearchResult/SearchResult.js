import React, { useState, useEffect } from 'react'
import HotelCard from '../AllHotels/HotelCard'
import HotelService from '../../../services/HotelService'
import RoomService from '../../../services/RoomService'


function SearchResult(props) {
    const [hotels, setHotels] = useState([])

    function filterResults() {
        for (let i = 0; i < hotels.length; i++) {
            if (RoomService.getNrOfAvailableRoomsOfHotel(props.location.state.checkIn, props.location.state.checkOut, hotels[i].id) === 0) {
                delete hotels[i];
            }
        }
    }

    useEffect(() => {
        HotelService.getHotelsOfCity(props.location.state.destination, setHotels);
        filterResults();

    }, []);

    let header = "";

    if (props.location.state.children === "") {
        header = props.location.state.destination + ", " + props.location.state.checkIn + " - " + props.location.state.checkOut + ": " + hotels.length + " hotels were found -  " + props.location.state.adults + " adult(s)";
    }
    else {
        header = props.location.state.destination + ", " + props.location.state.checkIn + " - " + props.location.state.checkOut + ": " + hotels.length + " hotels were found -  " + props.location.state.adults + " adult(s), "
            + props.location.state.children + " children";
    }

    return (
        <div className="all-hotels">
            <div className="hotels-filter-bar">
                <div className="cards">
                    <div className="results-header">
                        <p>{header}</p>
                    </div>
                    {hotels.map(hotel => (
                        <HotelCard name={hotel.name} city={hotel.city} country={hotel.country} rating={hotel.nrStars} id={hotel.id}
                            checkIn={props.location.state.checkIn} checkOut={props.location.state.checkOut} nrAdults={props.location.state.adults} nrChildren={props.location.state.children} />
                    )
                    )}
                </div>
            </div>
        </div>
    )
}

export default SearchResult
