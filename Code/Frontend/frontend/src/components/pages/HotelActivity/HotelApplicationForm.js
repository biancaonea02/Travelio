import React, { useState } from 'react'
import AuthenticationService from '../../../services/AuthenticationService'
import HotelApplicationService from '../../../services/HotelApplicationService';
import './HotelActivity.css'


function HotelApplicationForm() {

    const currentUser = AuthenticationService.getCurrentUser();

    const [application, setApplication] = useState({
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


    const [submitted, setSubmitted] = useState(false);
    const [message, setMessage] = useState("");
    const [valid, setValid] = useState(false);

    const handleNameInputChange = (event) => {
        setApplication({ ...application, name: event.target.value })
    }

    const handleCityInputChange = (event) => {
        setApplication({ ...application, city: event.target.value })
    }
    const handleCountryInputChange = (event) => {
        setApplication({ ...application, country: event.target.value })
    }

    const handleAddressInputChange = (event) => {
        setApplication({ ...application, address: event.target.value })
    }

    const handleRatingInputChange = (event) => {
        setApplication({ ...application, rating: event.target.value })
    }

    const handlePhoneNumberInputChange = (event) => {
        setApplication({ ...application, phoneNumber: event.target.value })
    }

    const handleFreeParkingInputChange = (event) => {
        setApplication({ ...application, freeParking: event.target.value })
    }

    const handleFreeCancellationInputChange = (event) => {
        setApplication({ ...application, freeCancellation: event.target.value })
    }

    const handleTopLocationInputChange = (event) => {
        setApplication({ ...application, topLocation: event.target.value })
    }

    const handleDescriptionInputChange = (event) => {
        setApplication({ ...application, description: event.target.value })
    }

    const handleNewFacilityInputChange = (event) => {
        setApplication({ ...application, newFacility: event.target.value })
    }

    function addFacility() {
        setApplication({ ...application, facilities: application.facilities + application.newFacility + "," })
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        if (application.address && application.city && application.country && application.description && application.facilities
            && application.name && application.name && application.phoneNumber
            && application.rating) {

            setValid(true);

            console.log(application.freeCancellation)
            HotelApplicationService.addHotelApplication(application.name, application.city, application.country, application.address, application.rating, application.phoneNumber,
                application.freeParking, application.freeCancellation, application.topLocation, application.description, application.facilities.slice(0, -1))
                .then(
                    () => {
                        setMessage("Your hotel application was successfully sent! You can see the status of your application in 'Hotel Activity' page")
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
            <h1 className="application-banner">Hotel Application Form</h1>
            <form className="hotel-application-form" onSubmit={handleSubmit}>
                {submitted && valid ? <div className="add-application-success-message">{message}</div> : null}
                <h2 className="applicant-details">Your Personal Details</h2>
                <label className="names-group">Full Name</label>
                <div className="applicant-names">
                    <input type="text" readOnly="true" className="application-item" name="firstName" placeholder={currentUser.firstName} />
                    <input type="text" readOnly="true" className="application-item" name="lastName" placeholder={currentUser.lastName} />
                </div>
                <div className="application-detail">
                    <label className="names-group">E-mail</label>
                    <input type="text" readOnly="true" className="application-item" name="email" placeholder={currentUser.email} />
                </div>
                <h2 className="hotel-application-details">Your Hotel's Details</h2>
                <div className="application-detail">
                    <label className="names-group">Name*</label>
                    <input
                        onChange={handleNameInputChange}
                        value={application.name}
                        type="text"
                        className="application-item"
                        name="hotelName" />
                </div>
                {submitted && !application.name ? <span className="error" id="name-error">Please enter the name of your hotel</span> : null}
                <div className="application-detail">
                    <label className="names-group">City*</label>
                    <input
                        onChange={handleCityInputChange}
                        value={application.city}
                        type="text"
                        className="application-item"
                        name="hotelCity" />
                </div>
                {submitted && !application.city ? <span className="error" id="name-error">Please enter the city</span> : null}
                <div className="application-detail">
                    <label className="names-group">Country*</label>
                    <input
                        onChange={handleCountryInputChange}
                        value={application.country}
                        type="text"
                        className="application-item"
                        name="hotelCountry" />
                </div>
                {submitted && !application.country ? <span className="error" id="name-error">Please enter the country</span> : null}
                <div className="application-detail">
                    <label className="names-group">Address*</label>
                    <input
                        onChange={handleAddressInputChange}
                        value={application.address}
                        type="text"
                        className="application-item"
                        name="hotelAddress" />
                </div>
                {submitted && !application.address ? <span className="error" id="name-error">Please enter the address</span> : null}
                <div className="application-detail">
                    <label className="names-group">Rating*</label>
                    <select
                        onChange={handleRatingInputChange}
                        value={application.rating}
                        className="application-item"
                        name="hotelRating">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
                {submitted && !application.rating ? <span className="error" id="name-error">Please select the rating</span> : null}
                <div className="application-detail">
                    <label className="names-group">Phone Number*</label>
                    <input
                        onChange={handlePhoneNumberInputChange}
                        value={application.phoneNumber}
                        type="text"
                        className="application-item"
                        name="hotelPhoneNumber" />
                </div>
                {submitted && !application.phoneNumber ? <span className="error" id="name-error">Please enter the phone number</span> : null}
                <div className="application-detail">
                    <label className="names-group">Free Parking*</label>
                    <select
                        onChange={handleFreeParkingInputChange}
                        value={application.freeParking}
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
                        value={application.freeCancellation}
                        className="application-item"
                        name="hotelRating">
                        <option value="true">Yes</option>
                        <option value="false">No</option>
                    </select>
                </div>
                <div className="application-detail">
                    <label className="names-group">Top Location*</label>
                    <select
                        onChange={handleTopLocationInputChange}
                        value={application.topLocation}
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
                        value={application.description}
                        className="application-item"
                        name="hotelDescription"
                        rows="4"
                        cols="50" />
                </div>
                {submitted && !application.description ? <span className="error" id="name-error">Please enter the description</span> : null}
                <div className="application-detail">
                    <label className="names-group">All Facilities</label>
                    <input
                        value={application.facilities}
                        type="text"
                        readOnly="true"
                        className="application-item"
                        name="hotelFacilities" />
                </div>
                {submitted && !application.facilities ? <span className="error" id="name-error">Please enter the facilities</span> : null}
                <div className="application-detail">
                    <label className="names-group">Add new facility*</label>
                    <div className="add-new-facility">
                        <input
                            onChange={handleNewFacilityInputChange}
                            value={application.newFacility}
                            type="text"
                            className="application-item"
                            name="hotelNewFacility" />
                        <button type="button" className="add-new-facility-btn" onClick={addFacility}>Add facility</button>
                    </div>
                </div>
                <button type="submit" className="send-hotel-application">Send Application</button>
            </form>
        </div>
    )
}

export default HotelApplicationForm
