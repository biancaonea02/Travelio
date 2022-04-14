import React, { useState, useEffect } from 'react'
import { FaAlignCenter, FaCheckCircle, FaMinusCircle, FaHourglassHalf } from 'react-icons/fa';
import HotelApplicationService from '../../../services/HotelApplicationService';
import StatisticsCard from '../AdminView/Dashboard/StatisticsCard';
import './HotelActivity.css'
import { Link } from 'react-router-dom'
import DisplayedHotels from './DisplayedHotels/DisplayedHotels';
import HotelReservations from './HotelReservations/HotelReservations'
import HotelService from '../../../services/HotelService';
import AuthenticationService from '../../../services/AuthenticationService';

function HotelActivity() {

    const [hotelApplication, setHotelApplications] = useState([])
    const [acceptedHotelApplications, setAcceptedHotelApplications] = useState([])
    const [declinedHotelApplications, setDeclinedHotelApplications] = useState([])
    const [pendingHotelApplications, setPendingHotelApplications] = useState([])
    const [hotelsOwned, setHotelsOwned] = useState([]);

    useEffect(() => {
        HotelApplicationService.getHotelApplicationsOfUser(setHotelApplications);
        HotelApplicationService.getAcceptedHotelApplicationsOfUser(setAcceptedHotelApplications);
        HotelApplicationService.getDeclinedHotelApplicationsOfUser(setDeclinedHotelApplications);
        HotelApplicationService.getPendingHotelApplicationsOfUser(setPendingHotelApplications);
        HotelService.getHotelsOfOwner(setHotelsOwned, AuthenticationService.getCurrentUser().id);
    }, []);

    return (
        <div className="hotel-activity">
            <div className="hotel-application-cards">
                <StatisticsCard number={hotelApplication.length} name={"All Applications"} logo={FaAlignCenter} />
                <StatisticsCard number={acceptedHotelApplications.length} name={"Accepted Applications"} logo={FaCheckCircle} />
                <StatisticsCard number={declinedHotelApplications.length} name={"Declined Applications"} logo={FaMinusCircle} />
                <StatisticsCard number={pendingHotelApplications.length} name={"Pending Applications"} logo={FaHourglassHalf} />
            </div>
            <div className="redirect-application-form">
                <Link to="/hotel-activity/start-new-form" className="hotel-application-complete-form">Start a new form</Link>
            </div>
            {hotelsOwned.length !== 0 ?
                <DisplayedHotels />
                :
                null
            }
            {hotelsOwned.length !== 0 ?
                <HotelReservations />
                :
                null
            }
        </div>
    )
}

export default HotelActivity
