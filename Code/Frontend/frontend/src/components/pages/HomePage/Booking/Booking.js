import React, { useState } from 'react';
import './Booking.css'
import { FaSearch } from 'react-icons/fa';
import { Link, Redirect } from 'react-router-dom'
import DestinationsBooking from './DestinationsBooking';

function Booking() {
    const [search, setSearch] = useState({
        destination: "",
        checkIn: "",
        checkOut: "",
        adults: "",
        children: "",
    });


    const [submitted, setSubmitted] = useState(false);
    const [valid, setValid] = useState(false);
    const [redirect, setRedirect] = useState(false);
    const [message, setMessage] = useState("");


    const handleDestinationInputChange = (event) => {
        setSearch({ ...search, destination: event.target.value })
    }

    const handleCheckInInputChange = (event) => {
        setSearch({ ...search, checkIn: event.target.value })
    }
    const handleCheckOutInputChange = (event) => {
        setSearch({ ...search, checkOut: event.target.value })
    }

    const handleAdultsInputChange = (event) => {
        setSearch({ ...search, adults: event.target.value })
    }
    const handleChildrenInputChange = (event) => {
        setSearch({ ...search, children: event.target.value })
    }


    const handleSubmit = (event) => {
        console.log(submitted);
        event.preventDefault();
        if ((search.destination && search.checkIn && search.checkOut && search.adults && search.children) || (search.destination &&
            search.adults)) {
            var from = search.checkIn;
            var to = search.checkOut;

            var splitFrom = from.split('/');
            var splitTo = to.split('/');

            var fromDate = Date.parse(splitFrom[0], splitFrom[1] - 1, splitFrom[2]);
            var toDate = Date.parse(splitTo[0], splitTo[1] - 1, splitTo[2]);

            if (fromDate > toDate) {
                setMessage("The check-in date can't be after the check-out date")
            }

            else if (fromDate < Date.now()) {
                setMessage("The check-in date can't be before today's date")
            }
            else {
                setValid(true);
                setRedirect(true);
            }
        }

        setSubmitted(true);
        console.log(submitted);

    }


    return (
        <section className="booking">
            <div className="container-booking">
                <form action="" className="form-booking" onSubmit={handleSubmit}>
                    <div className="user-details">
                        <div className="input-group-booking">
                            <label for="destination" className="input-label">Destination</label>
                            <DestinationsBooking onChange={handleDestinationInputChange} value={search.destination} />
                            {submitted && !search.destination ? <span className="error" id="destination-error">Please select a destination</span> : null}
                        </div>
                        <div className="input-group-booking">
                            <label for="check-in" className="input-label">Check in</label>
                            <input
                                onChange={handleCheckInInputChange}
                                value={search.checkIn}
                                type="date"
                                className="input-booking"
                                id="checkIn"
                                placeholder="Select your check-in date" />
                            {message != "" ? <span className="error" id="destination-error">{message}</span> : null}
                        </div>
                        <div className="input-group-booking">
                            <label for="check-out" className="input-label">Check out</label>
                            <input
                                onChange={handleCheckOutInputChange}
                                value={search.checkOut}
                                type="date"
                                className="input-booking"
                                id="checkOut"
                                placeholder="Your check-out date"
                            />
                        </div>
                        <div className="input-group-booking">
                            <label for="adults" className="input-label">Adults</label>
                            <select
                                className="option-booking"
                                id="adults"
                                onChange={handleAdultsInputChange}
                                value={search.adults}>
                                <option value="0">0</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            {submitted && !search.adults ? <span className="error" id="destination-error">Please select the nr. of adults</span> : null}
                        </div>
                        <div className="input-group-booking">
                            <label for="children" className="input-label">Children</label>
                            <select
                                className="option-booking"
                                id="children"
                                onChange={handleChildrenInputChange}
                                value={search.children}>
                                <option value="0">0</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        <Link to="/" className="btn-search-booking" onClick={handleSubmit}>Search
                            <span className="search-icon"><FaSearch /></span>
                        </Link>
                        {redirect ? <Redirect
                            to={{
                                pathname: "/search-result",
                                state:
                                {
                                    destination: search.destination,
                                    checkIn: search.checkIn,
                                    checkOut: search.checkOut,
                                    adults: search.adults,
                                    children: search.children,
                                }
                            }}
                        /> : null}
                    </div>
                </form>
            </div>
        </section>
    )
}

export default Booking
