import React from 'react'
import { FaUser } from 'react-icons/fa';
import {Link } from 'react-router-dom';

function RoomBox(props) {
    const capacityRoom = [];
    for (let i = 1; i <= props.capacity; i++) {
        capacityRoom.push(<FaUser />);
    }
    return (
        <tr className="room-info">
            <td className="room-capacity">{capacityRoom}</td>
            <td className="room-type">
                {props.type}
            </td>
            <td className="room-facilities">{props.facilities}</td>
            <td className="room-dimension">{props.dimension}</td>
            <td className="room-pricePerNight">€{props.pricePerNight}
                <Link
                    to={{
                        pathname: `/all-hotels/${props.name}/reserve`,
                        state:
                        {
                            id: props.id,
                            checkIn: props.checkIn,
                            checkOut: props.checkOut,
                            nrAdults: props.nrAdults,
                            nrChildren: props.nrChildren,
                            type: props.type,
                            pricePerNight: props.pricePerNight,
                            roomId: props.roomId
                        }
                    }}
                    className="reserve-room"
                >
                    Reserve room
                </Link>
            </td>
        </tr>
    )
}

export default RoomBox
