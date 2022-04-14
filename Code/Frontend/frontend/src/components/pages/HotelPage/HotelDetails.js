import React, { useState, useEffect } from 'react'
import { FaStar, FaStarHalf, FaMapMarkedAlt, FaParking, FaCommentDollar, FaMapMarkerAlt, FaTimes, FaPhone, FaArrowRight } from 'react-icons/fa';
import Slideshow from './Slideshow';
import './HotelDetails.css'
import RoomBox from './RoomBox';
import HotelService from '../../../services/HotelService';
import RoomService from '../../../services/RoomService';
import FavouriteHotelService from '../../../services/FavouriteHotelService';
import AuthenticationService from '../../../services/AuthenticationService';

function HotelDetails(props) {
    const [hotel, setHotel] = useState(Object)
    const [rooms, setRooms] = useState([]);
    const [hotelFacilities, setHotelFacilities] = useState([]);
    const [isFavourite, setIsFavourite] = useState();

    function convertDate(ds) {
        var day = ds.split("-")[2];
        var month = ds.split("-")[1];
        var year = ds.split("-")[0];
        return (year + "/" + month + "/" + day)

    }

    if ('user' in localStorage) {
        FavouriteHotelService.isHotelSavedToFavourites(hotel.id, setIsFavourite)
    }

    function likeHotel() {
        FavouriteHotelService.addHotelToFavourites(hotel.id, AuthenticationService.getCurrentUser().id)

    }

    function removeHotel() {
        FavouriteHotelService.removeHotelFromFavourites(hotel.id, setIsFavourite)
    }

    useEffect(() => {
        HotelService.getHotelById(props.location.state.id, setHotel, setHotelFacilities);
        if (props.location.state.checkIn !== undefined && props.location.state.checkOut !== undefined) {
            RoomService.getAvailableRoomsOfHotel(convertDate(props.location.state.checkIn), convertDate(props.location.state.checkOut), props.location.state.id, setRooms);
        }
    }, []);



    let iconStyles = { color: "var(--yellow-solid)" };
    const stars = [];
    let j = 0;
    for (let i = 1; i <= hotel.nrStars; i++) {
        stars.push(<FaStar style={iconStyles} />);
        j = i;
    }
    if (props.rating - j == 0.5) {
        stars.push(<FaStarHalf style={iconStyles} />);
    }

    console.log(props.location.state.checkIn);
    console.log(props.location.state.checkOut);

    return (
        <div className="hotel-container">
            <div className="hotel-header">
                <div className="hotel-details">
                    <h1 className="hotel-details-name">{hotel.name} {stars} </h1>
                    <span className="hotel-details-address"><FaMapMarkedAlt className="pin" />{hotel.address}, {hotel.city}, {hotel.country}</span>
                    <span className="hotel-details-phoneNr"><FaPhone className="pin-phone" />{hotel.phoneNumber}</span>
                    {
                        'user' in localStorage ?
                            !isFavourite ?
                                <button className="add-hotel-to-favourites" onClick={likeHotel}>Save hotel to favourites</button>
                                :
                                <button className="add-hotel-to-favourites" onClick={removeHotel}>Remove from favourites</button>
                            :
                            null
                    }
                </div>
            </div>
            <div className="slider-container">
                <div className="slider-images">
                    <Slideshow name={hotel.name} />
                </div>
                <div className="general-info">
                    <h1 className="most-wanted">What people are looking for</h1>
                    <br />
                    {hotel.freeParking ? <span className="parking"><FaParking className="pin-parking" />  Free parking</span> : <span className="parking"><FaTimes className="pin-x" />  Free parking</span>}
                    <br />
                    <br />
                    <br />
                    {hotel.freeCancellation ? <span className="cancelation"><FaCommentDollar className="pin-cancelation" />  Free cancelation</span> : <span className="cancelation"><FaTimes className="pin-x" />  Free cancelation</span>}
                    <br />
                    <br />
                    <br />
                    {hotel.topLocation ? <span className="top-location"><FaMapMarkerAlt className="pin-top-location" />  Top location</span> : <span className="top-location"><FaTimes className="pin-x" />  Top location</span>}
                </div>
            </div>
            <div className="hotel-details-description">
                <p>{hotel.description}</p>
            </div>
            <div className="hotel-facilities">
                <h1 className="most-apreciated">Most apreciated facilities</h1>
                {hotelFacilities.map(facility => (
                    <p className="facility"><FaArrowRight />{facility}</p>
                )
                )}
            </div>
            {"user" in localStorage && props.location.state.checkIn !== undefined && props.location.state.checkOut !== undefined ?
                <div className="rooms-table">
                    <h1 className="dates-heading">Available rooms for your stay: {props.location.state.checkIn} - {props.location.state.checkOut}</h1>
                    <table className="rooms" id="rooms">
                        <th className="room-details">Capacity</th>
                        <th className="room-details">Type of the room</th>
                        <th className="room-details">Facilities</th>
                        <th className="room-details">Dimesnion</th>
                        <th className="room-details">Price per night</th>
                        {rooms.map(room => (
                            <RoomBox capacity={room.capacity} type={room.type} pricePerNight={room.pricePerNight} dimension={room.dimension} facilities={room.facilities} id={props.location.state.id}
                                checkIn={props.location.state.checkIn} checkOut={props.location.state.checkOut} nrAdults={props.location.state.nrAdults} nrChildren={props.location.state.nrChildren}
                                name={hotel.name} roomId={room.id}></RoomBox>
                        )
                        )}
                    </table>
                </div>
                :
                null
            }
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
        </div>
    )
}


export default HotelDetails
