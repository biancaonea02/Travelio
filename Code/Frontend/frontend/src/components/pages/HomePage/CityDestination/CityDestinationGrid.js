import React, { useState, useEffect } from 'react'
import './CityDestination.css'
import CityDestination from './CityDestination';
import axios from 'axios';

function CityDestinations() {
    const [destinations, setDestinations] = useState([])

    useEffect(() => {
        axios.get('http://localhost:8080/hotel')
            .then(res => {
                const hotels = res.data;
                setDestinations(hotels);
            })
    }, [])

    const slicedArray = destinations.slice(0, 3);
    function nrHotelsFromCity(city) {
        let nr = 0;
        for (let i = 0; i < destinations.length; i++) {
            if (destinations[i].city == city) {
                nr++;
            }
        }
        return nr;
    }


    return (
        <section className="rooms">
            <div className="container-rooms">
                <h5 className="section-head-rooms">
                    <span className="heading-rooms">Your new adventure</span>
                    <span className="sub-heading-rooms">could start here</span>

                </h5>
                <div className="grid rooms-grid">
                    {slicedArray.map(hotel => (
                        <CityDestination key={hotel.city} city={hotel.city} nrHotels={nrHotelsFromCity(hotel.city)} />
                    )
                    )}
                </div>
            </div>
        </section>
    )
}
export default CityDestinations
