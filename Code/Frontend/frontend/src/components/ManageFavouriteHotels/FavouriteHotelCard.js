import React from 'react'
import { FaStar, FaStarHalf } from 'react-icons/fa';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';

function FavouriteHotelCard(props) {
    let iconStyles = { color: "var(--yellow-solid)" };
    const stars = [];
    let j = 0;
    for (let i = 1; i <= props.nrStars; i++) {
        stars.push(<FaStar style={iconStyles} />);
        j = i;
    }
    if (props.rating - j === 0.5) {
        stars.push(<FaStarHalf style={iconStyles} />);
    }

    return (
        <div>
            <Card sx={{ maxWidth: 345 }}>
                <CardMedia
                    component="img"
                    alt="green iguana"
                    height="140"
                    image={process.env.PUBLIC_URL + '/img/' + props.hotelName + '.jpg'}
                />
                <CardContent>
                    <Typography className="favourite-hotel-name" gutterBottom variant="h5" component="div">
                        {props.hotelName} {stars}
                    </Typography>
                </CardContent>
            </Card>
        </div>
    )
}

export default FavouriteHotelCard
