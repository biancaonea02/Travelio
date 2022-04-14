import React from 'react'
import { Button } from '../Button/Button'
import { GiWorld } from 'react-icons/gi'
import './Navbar.css'

function NavbarAdmin(props) {
    return (
        <div>
            <div>
                <nav className="navbar">
                    <div className="navbar-container container">
                        <ul className='nav-menu'>
                            {props.currentAdmin ?
                                <a href='/admin/dashboard' className='navbar-logo'>
                                    <GiWorld className='navbar-icon' />
                                    TRAVELIO
                                </a>
                                :
                                <a className='navbar-logo'>
                                    <GiWorld className='navbar-icon' />
                                    TRAVELIO
                                </a>
                            }
                            {props.currentAdmin ?
                                <li className="nav-item">
                                    <a href='/admin/dashboard' className="nav-links">
                                        Dashboard
                                    </a>
                                </li>
                                :
                                null
                            }
                            {props.currentAdmin ?
                                <li className="nav-item">
                                    <a href="/admin/hotel-applications" className="nav-links">
                                        Applications
                                    </a>
                                </li>
                                :
                                null
                            }
                            {props.currentAdmin ?
                                <li className="nav-item">
                                    <a href="/admin/messages" className="nav-links">
                                        Messages
                                    </a>
                                </li>
                                :
                                null
                            }
                            {props.currentAdmin ?
                                <li className="nav-item">
                                    <a href="/admin/chat" className="nav-links">
                                        Chat
                                    </a>
                                </li>
                                :
                                null}
                        </ul>

                        {
                            props.currentAdmin ? (
                                <ul className='nav-menu'>
                                    <li className="nav-item">
                                        <a href="/admin/profile" className="nav-links">
                                            {props.currentAdmin.username}
                                        </a>
                                    </li>
                                    <li className="nav-btn">
                                        <a href="/admin" className='btn-link'>
                                            <Button buttonStyle='btn--sign-up' onClick={props.logOut}>LOG OUT</Button>
                                        </a>
                                    </li>
                                </ul>
                            ) : (
                                null
                            )
                        }
                    </div>
                </nav >
            </div>
        </div>
    )
}

export default NavbarAdmin
