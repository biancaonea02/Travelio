import React from 'react'
import 'react-icons/all'

function StatisticsCard(props) {
    return (
        <div class="card-single">
            <div>
                <h1>{props.number}</h1>
                <span class="user-detailss">{props.name}</span>
            </div>
            <div>
                <span><props.logo /></span>
            </div>
        </div>
    )
}

export default StatisticsCard
