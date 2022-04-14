import React, { useState, useEffect } from 'react'
import './Dashboard.css'
import StatisticsCard from './StatisticsCard'
import UserService from '../../../../services/UserService';
import HotelService from '../../../../services/HotelService';
import { FaAlignCenter, FaEnvelopeOpenText, FaHotel, FaUsers } from 'react-icons/fa';
import ReservationService from '../../../../services/ReservationService';
import MessageService from '../../../../services/MessageService';
import HotelStatistics from './HotelStatistics/HotelStatistics';
import UserStatistics from './UserStatistics/UserStatistics';
import ReservationStatistics from './ReservationStatistics/ReservationStatistics';
import HotelApplicationService from '../../../../services/HotelApplicationService';

function Dashboard() {

    const [users, setUsers] = useState([]);
    const [hotels, setHotels] = useState([]);
    const [reservations, setReservations] = useState([])
    const [messages, setMessages] = useState([])
    const [applications, setApplications] = useState([]);

    let cities = []
    let hotelNames = []

    const unique = (value, index, self) => {
        return self.indexOf(value) === index;
    }

    hotels.map(hotel => (
        cities.push(hotel.city))
    )

    hotels.map(hotel => (
        hotelNames.push(hotel.name))
    )

    cities = cities.filter(unique);


    useEffect(() => {
        UserService.getAllUsers(setUsers);
        HotelService.getAllHotels(setHotels);
        ReservationService.getAllReservations(setReservations)
        MessageService.getAllMessages(setMessages);
        HotelApplicationService.getAllHotelApplications(setApplications);
    }, [])

    return (
        < main >
            <div class="statistics-cards">
                <StatisticsCard number={users.length} name={"Users"} logo={FaUsers} />
                <StatisticsCard number={hotels.length} name={"Hotels"} logo={FaHotel} />
                <StatisticsCard number={applications.length} name={"Hotel Applications"} logo={FaAlignCenter} />
                <StatisticsCard number={messages.length} name={"Messages"} logo={FaEnvelopeOpenText} />
            </div>
            <HotelStatistics hotels={hotels.length} reservations={reservations.length} cities={cities.length}
                city1={cities[0]} city2={cities[1]} city3={cities[2]} city4={cities[3]}
                city5={cities[4]} city6={cities[5]} hotel1={hotelNames[0]} hotel2={hotelNames[2]} hotel3={hotelNames[4]}
                hotel4={hotelNames[6]} hotel5={hotelNames[8]} hotel6={hotelNames[10]} hotel7={hotelNames[7]} />

            <UserStatistics />
            <ReservationStatistics />
        </main >
    )
}

export default Dashboard
