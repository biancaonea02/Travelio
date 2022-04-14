import React from 'react'
import { Button } from '../Button/Button'
import { GiWorld } from 'react-icons/gi'
import './Navbar.css'

function NavbarUser(props) {
    return (
        <div>
            <nav className="navbar">
                <div className="navbar-container container">
                    <ul className='nav-menu'>
                        <a href='/home' className='navbar-logo'>
                            <GiWorld className='navbar-icon' />
                            TRAVELIO
                        </a>
                        <li className="nav-item">
                            <a href='/home' className="nav-links">
                                Home
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="/all-hotels" className="nav-links">
                                All Hotels
                            </a>
                        </li>
                        {props.currentUser ?
                            <li className="nav-item">
                                <a href="/hotel-activity" className="nav-links">
                                    Hotel Activity
                                </a>
                            </li>
                            :
                            null}
                        {props.currentUser ?
                            <li className="nav-item">
                                <a href="/my-reservations" className="nav-links">
                                    My Reservations
                                </a>
                            </li>
                            :
                            null}
                    </ul>

                    {
                        props.currentUser ? (
                            <ul className='nav-menu'>
                                <li className="nav-item">
                                    <a href="/profile" className="nav-links">
                                        {props.currentUser.username}
                                    </a>
                                </li>
                                <li className="nav-btn">
                                    <a href="/" className='btn-link'>
                                        <Button buttonStyle='btn--sign-up' onClick={props.logOut}>LOG OUT</Button>
                                    </a>
                                </li>
                            </ul>
                        ) : (
                            <ul className='nav-menu'>
                                <li className="nav-btn">
                                    <a href="/login" className='btn-link'>
                                        <Button buttonStyle='btn--sign-up'>LOG IN</Button>
                                    </a>
                                </li>

                                <li className="nav-btn">
                                    <a href="/sign-up" className='btn-link'>
                                        <Button buttonStyle='btn--sign-up'>SIGN UP</Button>
                                    </a>
                                </li>
                            </ul>
                        )
                    }
                </div>
            </nav >
        </div>
    )
}

export default NavbarUser
