import React, { useState } from 'react'
import './HotelCard.css'
import { FaStar, FaStarHalf } from 'react-icons/fa';
import { Redirect } from 'react-router-dom';


function HotelCard(props) {
    let iconStyles = { color: "var(--yellow-solid)" };
    const stars = [];
    let j = 0;
    for (let i = 1; i <= props.rating; i++) {
        stars.push(<FaStar style={iconStyles} />);
        j = i;
    }
    if (props.rating - j === 0.5) {
        stars.push(<FaStarHalf style={iconStyles} />);
    }

    const [redirect, setRedirect] = useState(false);


    return (
        <section className="hotel-card">
            <figure className="card">
                <div className="card-hero">
                    <img className="card-image" src={encodeURI(window.location.origin + "/img/" + "/" + props.name + '.jpg')} width="300px" height="200px"></img>
                </div>
                <div className="card-content">
                    <div className="card-info">
                        <p className="card-id">{props.id}</p>
                        <h5 className="card-name">{props.name} {stars}</h5>
                        <p className="card-address">{props.city}, {props.country}</p>
                    </div>
                    <div className="card-button">
                        <button className="card-btn" onClick={() => setRedirect(true)}>View More</button>
                    </div>
                    {redirect ? <Redirect
                        to={{
                            pathname: `/all-hotels/${props.name}`,
                            state:
                            {
                                id: props.id,
                                checkIn: props.checkIn,
                                checkOut: props.checkOut,
                                nrAdults: props.nrAdults,
                                nrChildren: props.nrChildren
                            }
                        }}
                    /> : null}
                </div>
            </figure>
        </section>
    )
}

export default HotelCard
