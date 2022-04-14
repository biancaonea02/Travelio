import axios from 'axios';

const API_URL = 'http://localhost:8080/room';

class RoomService {

    getRoomById(id) {
        axios.get(`${API_URL}/${id}`)
            .then(res => {
                return res.data;
            })
    }

    getRoomsOfHotel(idHotel, setRooms) {
        axios.get(API_URL + '/getHotelRooms', { params: { idHotel: idHotel } })
            .then(res => {
                setRooms(res.data);
            })

    }

    convertDate(ds) {
        var day = ds.split("-")[0];
        var month = ds.split("-")[1];
        var year = ds.split("-")[2];
        return new Date(year, month, day)

    }

    getAvailableRoomsOfHotel(checkIn, checkOut, idHotel, setRooms) {
        axios.get(API_URL + '/getAvailableHotelRooms', { params: { checkIn: checkIn, checkOut: checkOut, idHotel: idHotel } })
            .then(res => {
                setRooms(res.data);
            })

    }

    getNrOfAvailableRoomsOfHotel(checkIn, checkOut, idHotel) {
        axios.get(API_URL + '/getNrAvailableHotelRooms', { params: { checkIn: checkIn, checkOut: checkOut, idHotel: idHotel } })
            .then(res => {
                return res.data;
            })

    }


    getFirstAvailableRoomOfHotelOfType(type, checkIn, checkOut, hotelId, setRoom) {
        return axios({
            method: 'get',
            url: API_URL + '/getFirstAvailableHotelRoomOfType',
            headers: {},
            params: {
                type: type,
                checkIn: checkIn,
                checkOut: checkOut,
                hotelId:
                {
                    id: hotelId
                }

            }
        })
            .then(response => {
                setRoom(response.data)
                return response.data;
            });

    }
}

export default new RoomService();