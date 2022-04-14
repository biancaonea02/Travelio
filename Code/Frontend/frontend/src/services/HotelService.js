import axios from 'axios';
import AuthenticationService from './AuthenticationService';

const API_URL = 'http://localhost:8080/hotel';

class HotelService {
    getAllHotels(setHotels) {
        axios.get(API_URL)
            .then(res => {
                setHotels(res.data)
            })
    }

    getHotelById(id, setHotel, setHotelFacilities) {
        axios.get(`${API_URL}/${id}`)
            .then(res => {
                setHotel(res.data);
                setHotelFacilities(res.data.facilities.split(","));
            })

    }

    getHotelById2(id, setHotel) {
        axios.get(`${API_URL}/${id}`)
            .then(res => {
                setHotel(res.data);
            })

    }

    getHotelsOfCity(city, setHotels) {
        axios.get(API_URL + '/filterByCity', { params: { city: city } })
            .then(res => {
                setHotels(res.data);
            })
    }

    getNumberOfHotelsOfCity(city) {
        axios.get(API_URL + '/numberOfHotelsOfCity', { params: { city: city } })
            .then(res => {
                console.log(res.data);
                return res.data;
            })
    }

    getHotelsOfOwner(setHotels) {
        return axios({
            method: 'get',
            url: API_URL + `/hotelsOfOwner/${AuthenticationService.getCurrentUser().id}`,
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() }
        })
            .then(response => {
                if (response.data) {
                    setHotels(response.data)
                }
            });
    }

    getLastFiveHotels(setLastHotels) {
        return axios({
            method: 'get',
            url: API_URL + '/lastFive',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() }
        })
            .then(response => {
                if (response.data) {
                    setLastHotels(response.data)
                }
            });
    }

    updateHotel(hotelId, name, rating, phoneNumber, freeParking, freeCancellation,
        description, facilities) {
        const params = new URLSearchParams({
            hotelId: hotelId,
            name: name,
            rating: rating,
            phoneNumber: phoneNumber,
            freeParking: freeParking,
            freeCancellation: freeCancellation,
            description: description,
            facilities: facilities
        }).toString();

        return axios.post(API_URL + "/update?" + params, {}, {
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() }
        })
            .then(response => {
                return response.data
            });
    }

}

export default new HotelService();