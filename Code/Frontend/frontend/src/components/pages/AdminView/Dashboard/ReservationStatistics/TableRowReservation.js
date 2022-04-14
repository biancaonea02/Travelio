import React from 'react'

function TableRowReservation(props) {
    return (
        <div className="table-row">
            <div className="table-data">{props.hotelName}</div>
            <div className="table-data">{props.checkIn}</div>
            <div className="table-data">{props.checkOut}</div>
            <div className="table-data">{props.userEmail}</div>
            {
                props.statistics === true ?
                    <>
                        <div className="table-data">{props.typeOfRoom}</div>
                        <div className="table-data">{props.pricePerNight}</div>
                    </>
                    :
                    null
            }
        </div>
    )
}

export default TableRowReservation
