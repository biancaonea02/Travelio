import React, { useState, useEffect } from 'react'
import AuthenticationService from "../../../services/AuthenticationService";
import './ProfilePage.css'
import { FaEllipsisH } from 'react-icons/fa';
import { Link } from 'react-router-dom'
import ReservationService from "../../../services/ReservationService";
import FavouriteHotelService from '../../../services/FavouriteHotelService';
import ManageFavouriteHotels from '../../ManageFavouriteHotels/ManageFavouriteHotels';
import imgAdmin from "../../../img/user.png"


function ProfilePage() {
    const [currentUser, setCurrentUser] = useState(Object)
    const [currentAdmin, setCurrentAdmin] = useState(Object);
    const [userReservations, setUserReservations] = useState([])
    const [hotelsLikedByUser, setHotelsLikedByUser] = useState([])
    const path = window.location.pathname;

    useEffect(() => {
        setCurrentUser(AuthenticationService.getCurrentUser())
        setCurrentAdmin(AuthenticationService.getCurrentAdmin());
        ReservationService.getReservationOfUser(setUserReservations);
        FavouriteHotelService.getHotelsLikedByUser(setHotelsLikedByUser);
        console.log(userReservations);
    }, [])

    if (path.startsWith("/admin")) {
        return (
            <div className="profile-page">
                <div className="wrapper-profile">
                    <div className="left">
                        <img src={imgAdmin} alt="user" width="100" />
                        <h4>{currentAdmin.firstName} {currentAdmin.lastName}</h4>
                    </div>
                    <div className="right">
                        <div className="info">
                            <h3>Personal Information</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Username</h4>
                                    <p>{currentAdmin.username}</p>
                                </div>
                                <div className="data">
                                    <h4>E-mail</h4>
                                    <p>{currentAdmin.email}</p>
                                </div>
                                <div className="data">
                                    <h4>Registration Date</h4>
                                    <p>{currentAdmin.joinDate}</p>
                                </div>
                            </div>
                        </div>

                        <div className="projects">
                            <div className="social_media">
                                <ul>
                                    <Link to="/admin/profile/change-personal-info" className="btn-change-info">Change personal information
                                        <span className="dots-profile-page"><FaEllipsisH /></span>
                                    </Link>
                                    <Link to="/admin/profile/update-password" className="btn-update-pwd">Update password
                                        <span className="dots-profile-page"><FaEllipsisH /></span>
                                    </Link>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        )
    }
    return (
        <div>
            <div className="profile-page">
                <div className="wrapper-profile">
                    <div className="left">
                        <img src={`../img/user.png`} alt="user" width="100" />
                        <h4>{currentUser.firstName} {currentUser.lastName}</h4>
                    </div>
                    <div className="right">
                        <div className="info">
                            <h3>Personal Information</h3>
                            <div className="info_data">
                                <div className="data">
                                    <h4>Username</h4>
                                    <p>{currentUser.username}</p>
                                </div>
                                <div className="data">
                                    <h4>E-mail</h4>
                                    <p>{currentUser.email}</p>
                                </div>
                                <div className="data">
                                    <h4>Join Date</h4>
                                    <p>{currentUser.joinDate}</p>
                                </div>
                            </div>
                        </div>

                        <div className="projects">
                            <h3>Hotel Reservations Information</h3>
                            <div className="projects_data">
                                <div className="data">
                                    <h4>Number of reservations</h4>
                                    <p>{userReservations.length}</p>
                                </div>
                                <div className="data">
                                    <h4>Number of liked hotels</h4>
                                    <p>{hotelsLikedByUser.length}</p>
                                </div>
                            </div>
                            <div className="social_media">
                                <ul>
                                    <Link to="/profile/change-personal-info" className="btn-change-info">Change personal information
                                        <span className="dots-profile-page"><FaEllipsisH /></span>
                                    </Link>
                                    <Link to="/profile/update-password" className="btn-update-pwd">Update password
                                        <span className="dots-profile-page"><FaEllipsisH /></span>
                                    </Link>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <ManageFavouriteHotels />
        </div>

    )


}

export default ProfilePage



