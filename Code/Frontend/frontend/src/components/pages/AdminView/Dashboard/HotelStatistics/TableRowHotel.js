import React from 'react'

function TableRowHotel(props) {
    return (
        <div className="table-row-hotels">
            <div className="table-data">{props.name}</div>
            <div className="table-data">{props.city}</div>
            <div className="table-data">{props.country}</div>
        </div>
    )
}

export default TableRowHotel
