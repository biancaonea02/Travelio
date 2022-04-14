import React from 'react'
import { FaStar, FaStarHalf, FaEllipsisH, FaMapMarkedAlt } from 'react-icons/fa';
import { Link } from 'react-router-dom'

function Hotel(props) {
    let iconStyles = { color: "var(--yellow-solid)" };
    const stars = [];
    let j = 0;
    for (let i = 1; i <= props.rating; i++) {
        stars.push(<FaStar style={iconStyles} />);
        j = i;
    }
    if (props.rating - j == 0.5) {
        stars.push(<FaStarHalf style={iconStyles} />);
    }

    return (
        < div className="hotel" >
            <div className="grid-item featured-hotels">
                <img src={encodeURI(process.env.PUBLIC_URL + '/img/' + props.name + '.jpg')} alt="" className="hotel-image" />
                <h5 className="hotel-name">{props.name}</h5>
                <span className="hotel-price"><FaMapMarkedAlt className="pin" />{props.city}, {props.country}</span>
                <div className="hotel-rating">
                    {stars}
                </div>
                <Link
                    to={{
                        pathname: `/all-hotels/${props.name}`,
                        state:
                        {
                            id: props.id
                        }
                    }}
                    className="btn-explore-hotels"
                >
                    View More
                    <span className="dots-explore-hotels"><FaEllipsisH /></span>
                </Link>

            </div>
        </div >
    )
}

export default Hotel
