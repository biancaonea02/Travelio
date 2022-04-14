import React from 'react'
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { Link } from 'react-router-dom'

function DisplayedHotelCard(props) {
    return (
        <div>
            <Card sx={{ width: 350 }}>
                <CardContent>
                    <Typography variant="h5" component="div" className="user-reservation-hotel-name">
                        {props.hotelName}
                    </Typography>
                    <Typography variant="body2" className="user-reservation-details">
                        {props.country}, {props.city}
                    </Typography>
                    <Typography variant="body2" className="user-reservation-details">
                        {props.address}
                    </Typography>
                </CardContent>
                <CardActions>
                    <Link className="btn-delete-reservation"
                        to={{
                            pathname: `/update-hotel/${props.id}`,
                            state:
                            {
                                id: props.id
                            }
                        }}
                    >Update Hotel</Link>
                </CardActions>
            </Card>
        </div>
    )
}

export default DisplayedHotelCard
