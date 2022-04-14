import axios from 'axios';
import AuthenticationService from './AuthenticationService';

const API_URL = 'http://localhost:8080/favouriteHotel';

class FavouriteHotelService {

    getHotelsLikedByUser(setHotels) {
        return axios({
            method: 'get',
            url: API_URL + '/likedByUser',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
            params: {
                idUser: AuthenticationService.getCurrentUser().id
            }
        })
            .then(response => {
                setHotels(response.data)
            });
    }

    addHotelToFavourites(hotelId, userId) {
        return axios({
            method: 'post',
            url: API_URL + '/addHotelToFavourites',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
            data: {
                hotelId:
                {
                    id: hotelId
                },
                userId:
                {
                    id: userId
                }
            }
        })
            .then(response => {
                return response.data;
            });

    }

    isHotelSavedToFavourites(idHotel, setIsHotelSavedToFavourites) {
        return axios({
            method: 'get',
            url: API_URL + '/isHotelSaved',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
            params: {
                idUser: AuthenticationService.getCurrentUser().id,
                idHotel: idHotel
            }
        })
            .then(response => {
                setIsHotelSavedToFavourites(response.data)
                console.log(response.data)
            });
    }

    removeHotelFromFavourites(idHotel, setIsHotelSavedToFavourites) {
        return axios({
            method: 'delete',
            url: API_URL + '/delete',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
            params: {
                userId: AuthenticationService.getCurrentUser().id,
                hotelId: idHotel
            }
        })
            .then(response => {
                setIsHotelSavedToFavourites(response.data)
            });
    }
}

export default new FavouriteHotelService();