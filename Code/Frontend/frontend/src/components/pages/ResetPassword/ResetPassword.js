import React, { Component } from "react";
import UserService from "../../../services/UserService";
import './ResetPassword.css'
import { FaEllipsisH } from 'react-icons/fa';


export default class ResetPassword extends Component {
    constructor(props) {
        super(props);

        this.handleSubmit = this.handleSubmit.bind(this);
        this.onChangeEmail = this.onChangeEmail.bind(this);

        this.state = {
            email: "",
            message: "",
            loading: false,
            valid: false,
            submitted: false
        };
    }

    onChangeEmail(e) {
        this.setState({
            email: e.target.value
        });
    }


    handleSubmit = (event) => {
        event.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        if (this.state.email) {
            this.setState({
                valid: true
            });
            UserService.resetPassword(this.state.email).
                then(
                    () => {
                        this.setState({
                            message: "An e-mail with the new password was sent to: " + this.state.email
                        });
                    },
                    error => {
                        const resMessage =
                            (error.response &&
                                error.response.data &&
                                error.response.data.message) ||
                            error.message ||
                            error.toString();

                        this.setState({
                            loading: false,
                            message: resMessage
                        });
                    }
                );
        } else {
            this.setState({
                loading: false
            });
        }
        this.setState({
            submitted: true
        });

    }

    render() {

        return (
            <div className="profile-page">
                <div className="wrapper">
                    <div className="left">
                        <img src={`../img/user.png`} alt="user" width="100" />
                    </div>
                    <div className="right">
                        <div className="info">
                            <h3>Reset your password</h3>
                            <form className="update-personal-info" onSubmit={this.handleSubmit}>
                                {this.state.submitted && this.state.valid && this.state.message !== "" ? <div className="success-message-updated">{this.state.message}</div> : null}
                                <div className="data">
                                    <h4>Your e-mail account</h4>
                                    <input
                                        onChange={this.onChangeEmail}
                                        value={this.state.email}
                                        id="email"
                                        className="form-field"
                                        type="text"
                                        name="email"
                                    />
                                </div>
                                {this.state.submitted && !this.state.email ? <span className="error" id="email-error">Please enter an e-mail address</span> : null}
                                <button className="btn-update-info">Reset password
                                    <span className="dots-explore-hotels"><FaEllipsisH /></span></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}