import React from 'react'


function CityDestination(props) {
    return (
        <div className="grid-item featured-rooms">
            <div className="image-wrap">
                <img className="room-image" src={process.env.PUBLIC_URL + '/img/' + props.city + '.jpg'} alt="" />
                <h5 className="room-name">{props.city}</h5>
            </div>
            <div className="room-info-wrap">
                <span className="room-price">{props.nrHotels} <span className="per-night">Hotels</span></span>
                <br />
            </div>
        </div >
    )
}

export default CityDestination
