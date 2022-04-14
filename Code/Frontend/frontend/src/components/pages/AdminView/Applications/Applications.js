import React, { useState, useEffect } from 'react'
import HotelApplicationService from '../../../../services/HotelApplicationService';
import ApplicationCard from './ApplicationCard';
import './Applications.css'

function Applications() {
    const [pendingApplications, setPendingApplications] = useState([])
    const [acceptedApplications, setAcceptedApplications] = useState([]);
    const [declinedApplications, setDeclinedApplications] = useState([]);
    const [applicationsToShow, setApplicationsToShow] = useState([]);

    useEffect(() => {
        HotelApplicationService.getAllPendingHotelApplications(setPendingApplications);
        HotelApplicationService.getAllAcceptedHotelApplications(setAcceptedApplications);
        HotelApplicationService.getAllDeclinedHotelApplications(setDeclinedApplications);
        HotelApplicationService.getAllPendingHotelApplications(setApplicationsToShow);
    }, [])


    function showPendingApplications() {
        setApplicationsToShow(pendingApplications);
    }

    function showAcceptedApplications() {
        setApplicationsToShow(acceptedApplications);
    }

    function showDeclinedApplications() {
        setApplicationsToShow(declinedApplications);
    }

    return (
        <div className="all-reservations">
            <div className='filter-applications'>
                <button className='pending-applications' onClick={showPendingApplications}>Pending Applications</button>
                <button className='accepted-applications' onClick={showAcceptedApplications}>Accepted Applications</button>
                <button className='declined-applications' onClick={showDeclinedApplications}>Declined Applications</button>
            </div>
            <div className="user-applications">
                {applicationsToShow.map(app => (
                    <ApplicationCard id={app.id} email={app.userId.email} username={app.userId.username}
                        firstName={app.userId.firstName} lastName={app.userId.lastName} hotelName={app.name} />
                )
                )}
            </div>
        </div>
    );
}

export default Applications

