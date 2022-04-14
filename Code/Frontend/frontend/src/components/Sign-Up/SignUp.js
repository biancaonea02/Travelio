import React, { useState } from "react";
import './SignUp.css';
import '../../components/pages/HomePage/valencia.jpg'
import AuthenticationService from "../../services/AuthenticationService";

function SignUp() {

    const [values, setValues] = useState({
        firstName: "",
        lastName: "",
        username: "",
        email: "",
    });


    const [message, setMessage] = useState();
    const [submitted, setSubmitted] = useState(false);
    const [valid, setValid] = useState(false);
    const [loading, setLoading] = useState(false);


    const handleFirstNameInputChange = (event) => {
        setValues({ ...values, firstName: event.target.value })
    }

    const handleLastNameInputChange = (event) => {
        setValues({ ...values, lastName: event.target.value })
    }

    const handleUsernameInputChange = (event) => {
        setValues({ ...values, username: event.target.value })
    }

    const handleEmailInputChange = (event) => {
        setValues({ ...values, email: event.target.value })
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        setLoading(true);
        if (values.firstName && values.lastName && values.username && values.email) {
            setValid(true);
        }

        AuthenticationService.register(values.firstName, values.lastName, values.username, values.email).
            then(
                () => {
                    setMessage("Your account was created! The password was sent to the registered e-mail address")
                },
                error => {
                    const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();
                    setLoading(false);
                    if (resMessage === "An error occurred while processing the request") {
                        setMessage("Your account was created! The password was sent to the registered e-mail address");
                    }
                    else {
                        setMessage(resMessage);
                    }
                }
            );
        setSubmitted(true);
    }


    return (
        <div className="sign-up">
            <div className="form-container">
                <h1 className="greeting-sign-up">Sign up now!</h1>
                <p className="paragraph-greeting-sign-up">Create an account to manage you reservations, dicover new offers and a lot more!</p>
                <form className="register-form" onSubmit={handleSubmit}>
                    {/* Uncomment the next line to show the success message */}
                    {submitted && valid ? <div className="success-message">{message}</div> : null}
                    <input
                        onChange={handleFirstNameInputChange}
                        value={values.firstName}
                        id="firstName"
                        className="form-field"
                        type="text"
                        placeholder="First Name*"
                        name="firstName"
                    />
                    {submitted && !values.firstName ? <span className="error" id="firstName-error">Please enter your first name</span> : null}
                    <input
                        onChange={handleLastNameInputChange}
                        value={values.lastName}
                        id="lastName"
                        className="form-field"
                        type="text"
                        placeholder="Last Name*"
                        name="lastName"
                    />
                    {submitted && !values.lastName ? <span className="error" id="lastName-error">Please enter your last name</span> : null}
                    <input
                        onChange={handleUsernameInputChange}
                        value={values.username}
                        id="username"
                        className="form-field"
                        type="text"
                        placeholder="Username*"
                        name="username"
                    />
                    {submitted && !values.username ? <span className="error" id="username-error">Please enter an username</span> : null}
                    <input
                        onChange={handleEmailInputChange}
                        value={values.email}
                        id="email"
                        className="form-field"
                        type="text"
                        placeholder="E-mail*"
                        name="email"
                    />
                    {submitted && !values.email ? <span className="error" id="password-error">Please enter an e-mail address</span> : null}
                    <button className="form-field" id="btnSubmit" type="submit">
                        Register
                    </button>
                </form>
            </div>
        </div>
    );
}

export default SignUp

