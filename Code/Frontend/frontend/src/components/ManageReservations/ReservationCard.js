import React, { useState } from 'react'
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import ReservationService from '../../services/ReservationService';

function ReservationCard(props) {
    const [message, setMessage] = useState("")

    function calculateNrDays() {
        var from = props.checkIn;
        var to = props.checkOut;

        var splitFrom = from.split('/');
        var splitTo = to.split('/');

        var fromDate = Date.parse(splitFrom[0], splitFrom[1] - 1, splitFrom[2]);
        var toDate = Date.parse(splitTo[0], splitTo[1] - 1, splitTo[2]);

        const oneDay = 24 * 60 * 60 * 1000;
        const firstDate = new Date(fromDate);
        const secondDate = new Date(toDate);

        return Math.round(Math.abs((firstDate - secondDate) / oneDay));
    }

    function deleteReservation() {
        ReservationService.deleteReservation(props.id, setMessage);
    }


    return (
        <div>
            <Card sx={{ width: 350 }}>
                <CardContent>
                    <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                        Reservation
                    </Typography>
                    <Typography variant="h5" component="div" className="user-reservation-hotel-name">
                        {props.hotelName}
                    </Typography>
                    <Typography variant="body2" className="user-reservation-details">
                        Check-in: {props.checkIn}
                    </Typography>
                    <Typography variant="body2" className="user-reservation-details">
                        Check-out: {props.checkOut}
                    </Typography>
                    <Typography variant="body2" className="user-reservation-details">
                        Type of room: {props.typeOfRoom}
                    </Typography>
                    <Typography variant="body2" className="user-reservation-details">
                        Nr. of adults: {props.nrAdults}
                    </Typography>
                    <Typography variant="body2" className="user-reservation-details">
                        Nr. of children: {props.nrChildren}
                    </Typography>
                    <Typography variant="body2" className="user-reservation-details">
                        Total price: €{props.pricePerNight * calculateNrDays()}
                    </Typography>
                    <Typography variant="body2" className="user-reservation-message">
                        {message}
                    </Typography>
                </CardContent>
                {props.freeCancellation ?
                    <CardActions>
                        <button className="btn-delete-reservation"
                            onClick={deleteReservation}>Cancel reservation</button>
                    </CardActions>
                    :
                    null}
            </Card>
        </div>
    )
}

export default ReservationCard
