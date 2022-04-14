import React, { useState, useEffect } from 'react'
import { useLocation } from 'react-router-dom';
import Typography from '@mui/material/Typography';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardActionArea from '@mui/material/CardActions';
import HotelApplicationService from '../../../../services/HotelApplicationService';

function ApplicationDetails() {
    const location = useLocation();
    const { id } = location.state;
    const [application, setHotelApplication] = useState(Object)
    const [message, setMessage] = useState("");

    useEffect(() => {
        HotelApplicationService.getHotelApplicationById(id, setHotelApplication);
    }, [])

    function acceptApplication() {
        HotelApplicationService.acceptApplication(id, setMessage);
    }

    function declineApplication() {
        HotelApplicationService.declineApplication(id, setMessage);
    }

    function convertBooleanToString(field) {
        if (field === true) {
            return "Yes";
        }
        return "No";
    }

    console.log(application);

    return (
        <div className="make-reservation-details">
            <Card className="reservation-card" >
                <CardActionArea className="reservation-card-action-area">
                    <CardContent className="reservation-card-content">
                        <Typography gutterBottom variant="h5" fontWeight="500" fontSize="25px">
                            Details of the hotel applications
                        </Typography>
                        <Typography variant="body2" color="text.secondary" className="reservation-details">
                            <br />
                            <div className="reservation-detail">
                                Name of the hotel: {application.name}
                            </div>
                            <div className="reservation-detail">
                                City: {application.city}
                            </div>
                            <div className="reservation-detail">
                                Country: {application.country}
                            </div>
                            <div className="reservation-detail">
                                Address: {application.address}
                            </div>
                            <div className="reservation-detail">
                                Rating: {application.rating}
                            </div>
                            <div className="reservation-detail">
                                Phone Number: {application.phoneNumber}
                            </div>
                            <div className="reservation-detail">
                                Free Parking: {convertBooleanToString(application.freeParking)}
                            </div>
                            <div className="reservation-detail">
                                Free Cancellation: {convertBooleanToString(application.freeCancellation)}
                            </div>
                            <div className="reservation-detail">
                                Top Location: {convertBooleanToString(application.topLocation)}
                            </div>
                            <div className="reservation-detail">
                                Description: {application.description}
                            </div>
                            <div className="reservation-detail">
                                Facilities: {application.facilities}
                            </div>
                            {application.status === "PENDING"
                                ?
                                <div className='application-actions'>
                                    <button className="accept-application" onClick={acceptApplication}>
                                        Accept Application
                                    </button>
                                    <button className="decline-application" onClick={declineApplication}>
                                        Decline Application
                                    </button>
                                </div>
                                :
                                <div className="reservation-detail">
                                    Status: {application.status}
                                </div>
                            }
                        </Typography>
                        <br />
                        <span className="reservation-message">{message}</span>
                        <br />
                        <br />
                    </CardContent>
                </CardActionArea>
            </Card >
        </div>
    )
}

export default ApplicationDetails
