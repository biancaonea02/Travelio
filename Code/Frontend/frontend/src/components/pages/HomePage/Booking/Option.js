import React from 'react'

function OptionDestination(props) {
    return (
        <>
            <option value="" disabled selected hidden>Choose your destination</option>
            <option>{props.city}</option>
        </>
    )
}

export default OptionDestination
