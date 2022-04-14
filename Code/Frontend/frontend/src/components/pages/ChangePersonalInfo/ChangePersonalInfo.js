import React, { Component } from "react";
import UserService from "../../../services/UserService";
import './ChangePersonalInfo.css'
import { FaEllipsisH } from 'react-icons/fa';
import AuthenticationService from "../../../services/AuthenticationService";
import imgAdmin from "../../../img/user.png"


export default class ChangePersonalInfo extends Component {
    constructor(props) {
        super(props);

        this.handleSubmitUser = this.handleSubmitUser.bind(this);
        this.handleSubmitAdmin = this.handleSubmitAdmin.bind(this);
        this.onChangeNewUsername = this.onChangeNewUsername.bind(this);
        this.onChangeNewEmail = this.onChangeNewEmail.bind(this);
        this.onChangeNewUsernameAdmin = this.onChangeNewUsernameAdmin.bind(this);
        this.onChangeNewEmailAdmin = this.onChangeNewEmailAdmin.bind(this);

        this.state = {
            currentUser: AuthenticationService.getCurrentUser(),
            currentAdmin: AuthenticationService.getCurrentAdmin(),
            newUsername: AuthenticationService.getCurrentUser().username,
            newEmail: AuthenticationService.getCurrentUser().email,
            newUsernameAdmin: AuthenticationService.getCurrentAdmin().username,
            newEmailAdmin: AuthenticationService.getCurrentAdmin().email,
            message: "",
            loading: false,
            valid: false,
            submitted: false
        };
    }

    onChangeNewUsername(e) {
        this.setState({
            newUsername: e.target.value
        });
    }

    onChangeNewEmail(e) {
        this.setState({
            newEmail: e.target.value
        });
    }

    onChangeNewUsernameAdmin(e) {
        this.setState({
            newUsernameAdmin: e.target.value
        });
    }

    onChangeNewEmailAdmin(e) {
        this.setState({
            newEmailAdmin: e.target.value
        });
    }

    handleSubmitUser = (event) => {
        event.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        if (this.state.newUsername && this.state.newEmail) {
            this.setState({
                valid: true
            });
            let currentUsername = this.state.currentUser.username;
            let newUsername = this.state.newUsername;
            let newEmail = this.state.newEmail;
            UserService.updatePersonalInformation(currentUsername, newUsername, newEmail, true).
                then(
                    () => {
                        this.setState({
                            message: "Your profile was succesfully updated!"
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

    handleSubmitAdmin = (event) => {
        event.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        if (this.state.newUsernameAdmin && this.state.newEmailAdmin) {
            this.setState({
                valid: true
            });
            let currentUsername = this.state.currentAdmin.username;
            let newUsername = this.state.newUsernameAdmin;
            let newEmail = this.state.newEmailAdmin;
            UserService.updatePersonalInformation(currentUsername, newUsername, newEmail, false).
                then(
                    () => {
                        this.setState({
                            message: "Your profile was succesfully updated!"
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
        const path = window.location.pathname;
        if (path.startsWith("/admin")) {
            return (
                <div className="profile-page">
                    <div className="wrapper">
                        <div className="left">
                            <img src={imgAdmin} alt="user" width="100" />
                            <h4>{this.state.currentAdmin.firstName} {this.state.currentAdmin.lastName}</h4>
                        </div>
                        <div className="right">
                            <div className="info">
                                <h3>Personal Information</h3>
                                <form className="update-personal-info" onSubmit={this.handleSubmitAdmin}>
                                    {this.state.submitted && this.state.valid && this.state.message !== "" ? <div className="success-message-updated">{this.state.message}</div> : null}
                                    <div className="data">
                                        <h4>New Username</h4>
                                        <input
                                            onChange={this.onChangeNewUsernameAdmin}
                                            value={this.state.newUsernameAdmin}
                                            id="username"
                                            className="form-field"
                                            type="text"
                                            name="username"
                                        />
                                    </div>
                                    {this.state.submitted && !this.state.newUsernameAdmin ? <span className="error" id="username-error">Please enter an username</span> : null}
                                    <div className="data">
                                        <h4>New E-mail</h4>
                                        <input
                                            onChange={this.onChangeNewEmailAdmin}
                                            value={this.state.newEmailAdmin}
                                            id="email"
                                            className="form-field"
                                            type="text"
                                            name="email"
                                        />
                                    </div>
                                    {this.state.submitted && !this.state.newEmailAdmin ? <span className="error" id="username-error">Please enter an e-mail address</span> : null}
                                    <button className="btn-update-info">Save new personal data
                                        <span className="dots-explore-hotels"><FaEllipsisH /></span></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
        return (
            <div className="profile-page">
                <div className="wrapper">
                    <div className="left">
                        <img src={`../img/user.png`} alt="user" width="100" />
                        <h4>{this.state.currentUser.firstName} {this.state.currentUser.lastName}</h4>
                    </div>
                    <div className="right">
                        <div className="info">
                            <h3>Personal Information</h3>
                            <form className="update-personal-info" onSubmit={this.handleSubmitUser}>
                                {this.state.submitted && this.state.valid && this.state.message !== "" ? <div className="success-message-updated">{this.state.message}</div> : null}
                                <div className="data">
                                    <h4>New Username</h4>
                                    <input
                                        onChange={this.onChangeNewUsername}
                                        value={this.state.newUsername}
                                        id="username"
                                        className="form-field"
                                        type="text"
                                        name="username"
                                    />
                                </div>
                                {this.state.submitted && !this.state.newUsername ? <span className="error" id="username-error">Please enter an username</span> : null}
                                <div className="data">
                                    <h4>New E-mail</h4>
                                    <input
                                        onChange={this.onChangeNewEmail}
                                        value={this.state.newEmail}
                                        id="email"
                                        className="form-field"
                                        type="text"
                                        name="email"
                                    />
                                </div>
                                {this.state.submitted && !this.state.newEmail ? <span className="error" id="username-error">Please enter an e-mail address</span> : null}
                                <button className="btn-update-info">Save new personal data
                                    <span className="dots-explore-hotels"><FaEllipsisH /></span></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}