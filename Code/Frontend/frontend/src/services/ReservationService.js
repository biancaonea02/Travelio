import axios from 'axios';
import AuthenticationService from './AuthenticationService';

const API_URL = 'http://localhost:8080/reservation';

class ReservationService {

    addReservation(hotelId, userId, checkIn, checkOut, nrAdults, nrChildren, roomId) {
        // let body = {
        //     senderName: senderName,
        //     senderEmail: senderEmail,
        //     content: content,
        //     status: status
        // }
        // return axios.post(API_URL + "/addReservation?" + params, {}, {
        //     headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() }
        // })
        //     .then(response => {
        //         return response.data
        //     });
        return axios({
            method: 'post',
            url: API_URL + '/addReservation',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
            data: {
                hotelId:
                {
                    id: hotelId
                },
                userId:
                {
                    id: userId
                },
                checkIn: checkIn,
                checkOut: checkOut,
                nrAdults: nrAdults,
                nrChildren: nrChildren,
                roomId:
                {
                    id: roomId
                }
            }
        })
            .then(response => {
                return response.data;
            });

    }

    getReservationOfUser(setReservations) {
        return axios({
            method: 'get',
            url: API_URL + '/getUserReservation',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
            params: {
                idUser: AuthenticationService.getCurrentUser().id
            }
        })
            .then(response => {
                setReservations(response.data)
            });
    }

    getReservationsOfHotel(idHotel, setReservations) {
        return axios({
            method: 'get',
            url: API_URL + '/getHotelReservation',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
            params: {
                idHotel: idHotel
            }
        })
            .then(response => {
                setReservations(response.data)
                return response.data;
            });
    }

    deleteReservation(idReservation, setMessage) {
        return axios({
            method: 'delete',
            url: API_URL + `/delete/${idReservation}`,
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
        })
            .then(response => {
                setMessage(response.data)
            });
    }

    getAllReservations(setReservations) {
        axios.get(API_URL)
            .then(res => {
                setReservations(res.data)
            })
    }

    getLastFiveReservations(setLastReservations) {
        return axios({
            method: 'get',
            url: API_URL + '/getLastReservations',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() }
        })
            .then(response => {
                if (response.data) {
                    setLastReservations(response.data)
                }
            });
    }

    getReservationOfHotelOwner(setReservations, userId) {
        return axios({
            method: 'get',
            url: API_URL + `/getReservationsOfHotelOwner/${userId}`,
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() }
        })
            .then(response => {
                if (response.data) {
                    setReservations(response.data)
                }
            });
    }

}

export default new ReservationService();