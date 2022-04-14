import React, { useState } from 'react'
import { FaEllipsisH } from 'react-icons/fa';
import './Contact.css'
import MessageService from '../../../../services/MessageService';

function Contact() {
    const [values, setValues] = useState({
        name: "",
        email: "",
        content: ""
    });


    const [message, setMessage] = useState();
    const [submitted, setSubmitted] = useState(false);
    const [valid, setValid] = useState(false);
    const [loading, setLoading] = useState(false);


    const handleNameInputChange = (event) => {
        setValues({ ...values, name: event.target.value })
    }

    const handleEmailInputChange = (event) => {
        setValues({ ...values, email: event.target.value })
    }

    const handleContentInputChange = (event) => {
        setValues({ ...values, content: event.target.value })
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        setLoading(true);
        if (values.name && values.email && values.content) {
            setValid(true);
        }

        MessageService.sendMessage(values.name, values.email, values.content, "NOT ANSWERED").
            then(
                () => {
                    setMessage("Your message was send! You will receive an e-mail from one of our admins")
                },
                error => {
                    const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();

                    setLoading(false);
                    setMessage(resMessage);
                }
            );
        setSubmitted(true);
    }


    return (
        <section className="contact">
            <div className="container">
                <h5 className="section-head-contact">
                    <span className="heading-contact">Contact</span>
                    <span className="sub-heading-contact">Get in touch with us</span>
                </h5>
                <div className="contact-content">
                    <form className="form contact-form" onSubmit={handleSubmit}>
                        <div className="input-group-wrap">
                            {submitted && valid ? <div className="success-message">{message}</div> : null}
                            <div className="input-group">
                                <input
                                    onChange={handleNameInputChange}
                                    value={values.name}
                                    type="text"
                                    className="input"
                                    placeholder="Name" />
                                <span className="bar"></span>
                            </div>
                            {submitted && !values.name ? <span className="error" id="name-error">Please enter your name</span> : null}
                            <div className="input-group">
                                <input
                                    onChange={handleEmailInputChange}
                                    value={values.email}
                                    type="email"
                                    className="input"
                                    placeholder="E-mail" />
                                <span className="bar"></span>
                            </div>
                            {submitted && !values.email ? <span className="error" id="email-error">Please enter your email</span> : null}
                            <div className="input-group">
                                <textarea
                                    onChange={handleContentInputChange}
                                    value={values.content}
                                    className="input"
                                    cols="30"
                                    rows="8"
                                    id="message"
                                    placeholder="Message"></textarea>
                                <span className="bar"></span>
                            </div>
                            {submitted && !values.content ? <span className="error" id="content-error">Please enter your message</span> : null}
                        </div>
                        <button type="submit" className="btn-search-booking">Send
                            <span className="search-icon"><FaEllipsisH /></span>
                        </button>
                    </form>
                </div>
            </div>
        </section>
    )
}

export default Contact
