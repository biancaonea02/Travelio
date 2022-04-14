import React, { useState, useEffect } from 'react'
import HotelCard from './HotelCard';
import './AllHotels.css'
import HotelService from '../../../services/HotelService';


function AllHotelsProba() {

    const [allHotels, setAllHotels] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");

    useEffect(() => {
        HotelService.getAllHotels(setAllHotels);
    }, [])


    return (
        <div className="all-hotels">
            <div className="cards">
                <div className='search-by-name'>
                    <h1 className='search-by-name-header'>Search hotel by name</h1>
                    <input className="search-name-box" type="text" placeholder="Search..." onChange={(event) => {
                        setSearchTerm(event.target.value);
                    }}></input>
                </div>
                {allHotels.filter((hotel) => {
                    if (searchTerm === "") {
                        return hotel;
                    } else if (hotel.name.toLowerCase().includes(searchTerm.toLowerCase())) {
                        return hotel;
                    }
                }).map(hotel => <HotelCard key={hotel.id} name={hotel.name} price={hotel.pricePerNight} city={hotel.city} country={hotel.country} rating={hotel.nrStars} id={hotel.id}
                    checkIn={null} checkOut={null} />)}
            </div>
        </div >
    )
}

export default AllHotelsProba
