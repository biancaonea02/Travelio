import React, { useState, useEffect } from 'react'
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';
import HotelService from '../../../services/HotelService';
import './Reserve.css'
import { FaEllipsisH } from 'react-icons/fa';
import ReservationService from '../../../services/ReservationService';
import AuthenticationService from '../../../services/AuthenticationService';

function Reserve(props) {

    const [hotel, setHotel] = useState(Object);
    const [message, setMessage] = useState("");

    function convertDate(ds) {
        var day = ds.split("-")[2];
        var month = ds.split("-")[1];
        var year = ds.split("-")[0];
        return (year + "/" + month + "/" + day)

    }

    useEffect(() => {
        HotelService.getHotelById2(props.location.state.id, setHotel);
        console.log(props.location.state.roomId);
    }, []);

    function calculateNrDays() {
        var from = props.location.state.checkIn;
        var to = props.location.state.checkOut;

        var splitFrom = from.split('/');
        var splitTo = to.split('/');

        var fromDate = Date.parse(splitFrom[0], splitFrom[1] - 1, splitFrom[2]);
        var toDate = Date.parse(splitTo[0], splitTo[1] - 1, splitTo[2]);

        const oneDay = 24 * 60 * 60 * 1000;
        const firstDate = new Date(fromDate);
        const secondDate = new Date(toDate);

        return Math.round(Math.abs((firstDate - secondDate) / oneDay));
    }
    console.log(props.location.state.nrChildren);

    function reserveRoom() {
        if(props.location.state.nrChildren === "")
        {
            props.location.state.nrChildren = 0;
        }
        ReservationService.addReservation(props.location.state.id, AuthenticationService.getCurrentUser().id, new Date(convertDate(props.location.state.checkIn)),
            new Date(convertDate(props.location.state.checkOut)), props.location.state.nrAdults, props.location.state.nrChildren, props.location.state.roomId)
            .then(
                () => {
                    setMessage("Your reservation was successfully saved! You can see all your reservation on your profile page.")
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

    console.log(props.location.state.nrChildren);

    return (
        <div className="make-reservation-details">
            <Card className="reservation-card" >
                <CardActionArea className="reservation-card-action-area">
                    <CardMedia
                        component="img"
                        image={encodeURI(process.env.PUBLIC_URL + '/img/' + hotel.name + '.jpg')}
                        alt="hotel picture"
                        className="reservation-hotel-image"
                    />
                    <CardContent className="reservation-card-content">
                        <Typography gutterBottom variant="h5" fontWeight="500" fontSize="25px">
                            {hotel.name}
                        </Typography>
                        <Typography variant="body2" color="text.black" fontWeight="bold" fontSize="20px">
                            <br />
                            Details of your reservation
                        </Typography>
                        <Typography variant="body2" color="text.secondary" className="reservation-details">
                            <br />
                            <div className="reservation-detail">
                                Check-in: {props.location.state.checkIn}
                            </div>
                            <div className="reservation-detail">
                                Check-out: {props.location.state.checkOut}
                            </div>
                            <div className="reservation-detail">
                                Type of room: {props.location.state.type}
                            </div>
                            <div className="reservation-detail">
                                Final price: €{props.location.state.pricePerNight * calculateNrDays()}
                            </div>
                            <div className="reservation-detail">
                                Nr. of adults: {props.location.state.nrAdults}
                            </div>
                            {props.location.state.nrChildren !== ""
                                ?
                                <div className="reservation-detail">
                                    Nr. of children: {props.location.state.nrChildren}
                                </div>
                                :
                                null
                            }
                        </Typography>
                        <br />
                        <span className="reservation-message">{message}</span>
                        <br />
                        <br />
                        <button className="reserve-hotel" onClick={reserveRoom}>
                            Reserve now
                            <span className="dots-reserve-hotel"><FaEllipsisH /></span>
                        </button>
                    </CardContent>
                </CardActionArea>
            </Card >
        </div>
    );
}

export default Reserve
