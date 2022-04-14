import axios from 'axios';
import AuthenticationService from './AuthenticationService';

const API_URL = 'http://localhost:8080/hotelApplication';

class HotelApplicationService {


    getHotelApplicationById(id, setHotelApplication) {
        return axios({
            method: 'get',
            url: API_URL + `/${id}`,
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
        })
            .then(response => {
                setHotelApplication(response.data)
            });
    }


    getAllHotelApplications(setHotelApplications) {
        return axios({
            method: 'get',
            url: API_URL,
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
        })
            .then(response => {
                setHotelApplications(response.data)
            });
    }


    getHotelApplicationsOfUser(setHotelApplications) {
        return axios({
            method: 'get',
            url: API_URL + '/user',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
            params: {
                userId: AuthenticationService.getCurrentUser().id
            }
        })
            .then(response => {
                setHotelApplications(response.data)
            });
    }

    getAcceptedHotelApplicationsOfUser(setHotelApplications) {
        return axios({
            method: 'get',
            url: API_URL + '/accepted',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
            params: {
                userId: AuthenticationService.getCurrentUser().id
            }
        })
            .then(response => {
                setHotelApplications(response.data)
            });
    }

    getDeclinedHotelApplicationsOfUser(setHotelApplications) {
        return axios({
            method: 'get',
            url: API_URL + '/declined',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
            params: {
                userId: AuthenticationService.getCurrentUser().id
            }
        })
            .then(response => {
                setHotelApplications(response.data)
            });
    }

    getPendingHotelApplicationsOfUser(setHotelApplications) {
        return axios({
            method: 'get',
            url: API_URL + '/pending',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
            params: {
                userId: AuthenticationService.getCurrentUser().id
            }
        })
            .then(response => {
                setHotelApplications(response.data)
            });
    }

    getAllPendingHotelApplications(setHotelApplications) {
        return axios({
            method: 'get',
            url: API_URL + '/pendingApplications',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
        })
            .then(response => {
                setHotelApplications(response.data)
            });
    }

    // getAllPendingHotelApplications(setHotelApplications) {
    //     return axios({
    //         method: 'get',
    //         url: API_URL + '/pendingApplications',
    //         headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
    //     })
    //         .then(response => {
    //             setHotelApplications(response.data)
    //         });
    // }

    getAllAcceptedHotelApplications(setHotelApplications) {
        return axios({
            method: 'get',
            url: API_URL + '/acceptedApplications',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
        })
            .then(response => {
                setHotelApplications(response.data)
            });
    }

    getAllDeclinedHotelApplications(setHotelApplications) {
        return axios({
            method: 'get',
            url: API_URL + '/declinedApplications',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
        })
            .then(response => {
                setHotelApplications(response.data)
            });
    }

    addHotelApplication(name, city, country, address, rating, phoneNumber, freeParking, freeCancellation, topLocation, description, facilities) {
        let body = {
            name: name,
            city: city,
            country: country,
            address: address,
            rating: rating,
            phoneNumber: phoneNumber,
            freeParking: freeParking,
            freeCancellation: freeCancellation,
            topLocation: topLocation,
            description: description,
            facilities: facilities,
            userId: AuthenticationService.getCurrentUser(),
            status: "PENDING"
        }
        return axios.post(API_URL + '/addHotelApplication', body, {
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() }
        })
            .then(response => {
                return response.data
            });
    }

    acceptApplication(id, setMessage) {
        return axios({
            method: 'post',
            url: API_URL + '/acceptHotelApplication',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
            params: {
                id: id
            }
        })
            .then(response => {
                setMessage(response.data);
                return response.data;
            });
    }

    declineApplication(id, setMessage) {
        return axios({
            method: 'post',
            url: API_URL + '/declineHotelApplication',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
            params: {
                id: id
            }
        })
            .then(response => {
                setMessage(response.data);
                return response.data;
            });
    }


}

export default new HotelApplicationService();