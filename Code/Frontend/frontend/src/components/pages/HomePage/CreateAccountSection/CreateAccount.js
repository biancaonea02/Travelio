import React from 'react'
import './CreateAccount.css'
import { FaEllipsisH } from 'react-icons/fa'
import { Link } from 'react-router-dom'

function CreateAccount() {
    return (
        <section className="create-account-section">
            <div className="create-account-content">
                <h1>Want to display your hotel on our website? Create an account today!</h1>
                <br />
                <h2>It's free of charge and you will be able to keep track of your hotel activity!</h2>
                <Link to="/sign-up" className="btn-home-signup">Sign Up Now
                    <span className="dots-explore-hotels"><FaEllipsisH /></span>
                </Link>
            </div>
        </section>
    )
}

export default CreateAccount
