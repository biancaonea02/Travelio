import React, { useState, useEffect } from 'react'
import axios from 'axios';
import OptionDestination from './Option';

function DestinationsBooking(props) {
    const [hotels, setHotels] = useState([])
    let cities = [];

    const unique = (value, index, self) => {
        return self.indexOf(value) === index;
    }

    useEffect(() => {
        axios.get('http://localhost:8080/hotel')
            .then(res => {
                const allHotels = res.data;
                setHotels(allHotels.filter(unique));
            })
    }, [])

    hotels.map(hotel => (
        cities.push(hotel.city))
    )

    cities = cities.filter(unique);


    return (
        <select
            className="option-booking"
            id="destination"
            onChange={props.onChange}
            value={props.value}
        >
            {cities.map(city => (
                <OptionDestination city={city} />
            )
            )}
        </select>
    )
}

export default DestinationsBooking
