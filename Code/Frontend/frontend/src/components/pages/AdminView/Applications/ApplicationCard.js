import React from 'react'
import './Applications.css'
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { deepPurple } from '@mui/material/colors';
import { Link } from 'react-router-dom'


function ApplicationCard(props) {
    const title = "Application for " + props.hotelName
    const applicantName = props.firstName + " " + props.lastName
    const ownerDetails = applicantName + " - " + props.email

    return (
        <Card sx={{ maxWidth: 375 }}>
            <CardHeader
                avatar={
                    <Avatar sx={{ bgcolor: deepPurple[500] }} aria-label="recipe">
                        {props.firstName[0]}{props.lastName[0]}
                    </Avatar>
                }
                title={ownerDetails}
            />
            <CardMedia
                component="img"
                height="170"
                image="/img/application.png"
                alt="Application Picture"
            />
            <CardContent>
                <Typography variant="body2" color="text.secondary">
                    {title}
                </Typography>
                <CardActions>
                    <Link className="btn-see-application-details"
                        to={{
                            pathname: `/admin/hotel-application`,
                            state:
                            {
                                id: props.id
                            }
                        }}
                    >See More Details</Link>
                </CardActions>
            </CardContent>
        </Card>
    );
}

export default ApplicationCard
