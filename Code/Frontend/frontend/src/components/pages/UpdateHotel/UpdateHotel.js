import React, { useState, useEffect } from 'react'
import { useLocation } from 'react-router-dom';
import HotelService from '../../../services/HotelService';

function UpdateHotel() {
    const location = useLocation();
    const { id } = location.state;
    const [newHotel, setNewHotel] = useState({
        name: "",
        city: "",
        country: "",
        address: "",
        rating: 1,
        phoneNumber: "",
        freeParking: true,
        freeCancellation: true,
        topLocation: true,
        description: "",
        facilities: "",
        newFacility: ""
    });

    useEffect(() => {
        HotelService.getHotelById2(id, setNewHotel);
    }, []);

    const [submitted, setSubmitted] = useState(false);
    const [message, setMessage] = useState("");
    const [valid, setValid] = useState(false);

    const handleNameInputChange = (event) => {
        setNewHotel({ ...newHotel, name: event.target.value })
    }

    const handleRatingInputChange = (event) => {
        setNewHotel({ ...newHotel, rating: event.target.value })
    }

    const handlePhoneNumberInputChange = (event) => {
        setNewHotel({ ...newHotel, phoneNumber: event.target.value })
    }

    const handleFreeParkingInputChange = (event) => {
        setNewHotel({ ...newHotel, freeParking: event.target.value })
    }

    const handleFreeCancellationInputChange = (event) => {
        setNewHotel({ ...newHotel, freeCancellation: event.target.value })
    }

    const handleDescriptionInputChange = (event) => {
        setNewHotel({ ...newHotel, description: event.target.value })
    }

    const handleNewFacilityInputChange = (event) => {
        setNewHotel({ ...newHotel, newFacility: event.target.value })
    }

    function addFacility() {
        setNewHotel({ ...newHotel, facilities: newHotel.facilities + "," + newHotel.newFacility + "," })
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        if (newHotel.address && newHotel.city && newHotel.country && newHotel.description && newHotel.facilities
            && newHotel.name && newHotel.name && newHotel.phoneNumber
            && newHotel.rating) {

            setValid(true);

            HotelService.updateHotel(id, newHotel.name, newHotel.rating, newHotel.phoneNumber,
                newHotel.freeParking, newHotel.freeCancellation, newHotel.description, newHotel.facilities.slice(0, -1))
                .then(
                    () => {
                        setMessage("Your hotel was successfully updated! The new information is available to the users!")
                    },
                    error => {
                        const resMessage =
                            (error.response &&
                                error.response.data &&
                                error.response.data.message) ||
                            error.message ||
                            error.toString();

                        setMessage(resMessage);
                    }
                );
        }

        setSubmitted(true);
        console.log(submitted);
    }


    return (
        <div className="form-add-hotel">
            <h1 className="application-banner">Update Hotel</h1>
            <form className="hotel-application-form" onSubmit={handleSubmit}>
                {submitted && valid ? <div className="add-application-success-message">{message}</div> : null}
                <h2 className="hotel-application-details">Hotel's Current Details</h2>
                <div className="application-detail">
                    <label className="names-group">Name</label>
                    <input
                        onChange={handleNameInputChange}
                        value={newHotel.name}
                        type="text"
                        className="application-item"
                        name="hotelName" />
                </div>
                {submitted && !newHotel.name ? <span className="error" id="name-error">Please enter the name of your hotel</span> : null}
                <div className="application-detail">
                    <label className="names-group">City (Cannot be changed)</label>
                    <input
                        value={newHotel.city}
                        type="text"
                        className="application-item"
                        name="hotelCity"
                        readOnly
                    />
                </div>
                {submitted && !newHotel.city ? <span className="error" id="name-error">Please enter the city</span> : null}
                <div className="application-detail">
                    <label className="names-group">Country (Cannot be changed)</label>
                    <input
                        value={newHotel.country}
                        type="text"
                        className="application-item"
                        name="hotelCountry"
                        readOnly />
                </div>
                {submitted && !newHotel.country ? <span className="error" id="name-error">Please enter the country</span> : null}
                <div className="application-detail">
                    <label className="names-group">Address (Cannot be changed)</label>
                    <input
                        value={newHotel.address}
                        type="text"
                        className="application-item"
                        name="hotelAddress"
                        readOnly />
                </div>
                {submitted && !newHotel.address ? <span className="error" id="name-error">Please enter the address</span> : null}
                <div className="application-detail">
                    <label className="names-group">Rating*</label>
                    <select
                        onChange={handleRatingInputChange}
                        value={newHotel.rating}
                        className="application-item"
                        name="hotelRating">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
                {submitted && !newHotel.rating ? <span className="error" id="name-error">Please select the rating</span> : null}
                <div className="application-detail">
                    <label className="names-group">Phone Number*</label>
                    <input
                        onChange={handlePhoneNumberInputChange}
                        value={newHotel.phoneNumber}
                        type="text"
                        className="application-item"
                        name="hotelPhoneNumber" />
                </div>
                {submitted && !newHotel.phoneNumber ? <span className="error" id="name-error">Please enter the phone number</span> : null}
                <div className="application-detail">
                    <label className="names-group">Free Parking*</label>
                    <select
                        onChange={handleFreeParkingInputChange}
                        value={newHotel.freeParking}
                        className="application-item"
                        name="hotelRating">
                        <option value="true">Yes</option>
                        <option value="false">No</option>
                    </select>
                </div>
                <div className="application-detail">
                    <label className="names-group">Free Cancellation*</label>
                    <select
                        onChange={handleFreeCancellationInputChange}
                        value={newHotel.freeCancellation}
                        className="application-item"
                        name="hotelRating">
                        <option value="true">Yes</option>
                        <option value="false">No</option>
                    </select>
                </div>
                <div className="application-detail">
                    <label className="names-group">Top Location (Cannot be changed)</label>
                    <select
                        value={newHotel.topLocation}
                        className="application-item"
                        name="hotelRating">
                        <option selected="true">Yes</option>
                        <option selected="false">No</option>
                    </select>
                </div>
                <div className="application-detail">
                    <label className="names-group">Description*</label>
                    <textarea
                        onChange={handleDescriptionInputChange}
                        value={newHotel.description}
                        className="application-item"
                        name="hotelDescription"
                        rows="4"
                        cols="50" />
                </div>
                {submitted && !newHotel.description ? <span className="error" id="name-error">Please enter the description</span> : null}
                <div className="application-detail">
                    <label className="names-group">All Facilities</label>
                    <input
                        value={newHotel.facilities}
                        type="text"
                        readOnly="true"
                        className="application-item"
                        name="hotelFacilities" />
                </div>
                {submitted && !newHotel.facilities ? <span className="error" id="name-error">Please enter the facilities</span> : null}
                <div className="application-detail">
                    <label className="names-group">Add new facility*</label>
                    <div className="add-new-facility">
                        <input
                            onChange={handleNewFacilityInputChange}
                            value={newHotel.newFacility}
                            type="text"
                            className="application-item"
                            name="hotelNewFacility" />
                        <button type="button" className="add-new-facility-btn" onClick={addFacility}>Add facility</button>
                    </div>
                </div>
                <button type="submit" className="send-hotel-application">Update Hotel</button>
            </form>
        </div>
    )
}

export default UpdateHotel
