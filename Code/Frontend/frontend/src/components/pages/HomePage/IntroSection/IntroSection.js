import React from 'react'
import { FaEllipsisH } from 'react-icons/fa';
import { Link } from 'react-router-dom'
import './IntroSection.css'

function IntroSection() {
    return (
        <div className="hero">
            <div className="container-hero">
                <div className="main-heading-hero">
                    <h1 className="title-hero">Discover</h1>
                    <h2 className="subtitle-hero">Luxury hotels</h2>
                </div>
                <Link to="/" className="btn-gradient-intro">Explore Now
                    <span className="dots"><FaEllipsisH /></span>
                </Link>
            </div>
        </div >

    )
}

export default IntroSection
