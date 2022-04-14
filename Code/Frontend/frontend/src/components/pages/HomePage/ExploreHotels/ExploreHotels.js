import React, { useState, useEffect } from 'react'
import './ExploreHotels.css'
import Hotel from './Hotel';
import axios from 'axios';

function ExploreHotels() {

    const [hotels, setHotels] = useState([])

    useEffect(() => {
        axios.get('http://localhost:8080/hotel')
            .then(res => {
                const allHotels = res.data;
                setHotels(allHotels);
            })
    }, [])

    const slicedArray = hotels.slice(0, 6);

    return (
        <section className="hotels">
            <div className="container-hotels">
                <h5 className="section-head-hotels">
                    <span className="heading-hotels">Explore</span>
                    <span className="sub-heading-hotels">Our beautiful hotels</span>
                </h5>
                <div className="grid-hotels">
                    {slicedArray.map(hotel => (
                        <Hotel name={hotel.name} city={hotel.city} country={hotel.country} rating={hotel.nrStars} id={hotel.id} />
                    )
                    )}
                </div>
            </div>
        </section>
    )

}

export default ExploreHotels
