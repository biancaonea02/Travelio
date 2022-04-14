import React, { useState, useEffect } from 'react'
import FavouriteHotelService from '../../services/FavouriteHotelService';
import FavouriteHotelCard from './FavouriteHotelCard';
import './ManageFavouriteHotels.css'

function ManageFavouriteHotels() {
    const [favouriteHotels, setFavouriteHotels] = useState([])

    useEffect(() => {
        FavouriteHotelService.getHotelsLikedByUser(setFavouriteHotels);
    }, []);


    return (
        <div className="favourite-hotels-of-user">
            <h2 className="favourite-hotels-heading">My favourite hotels</h2>
            <div className="favourite-hotels-grid">
                {favouriteHotels.map(hotel => (
                    <FavouriteHotelCard hotelName={hotel.name} nrStars={hotel.rating} />
                )
                )}
            </div>
        </div>
    )
}

export default ManageFavouriteHotels
