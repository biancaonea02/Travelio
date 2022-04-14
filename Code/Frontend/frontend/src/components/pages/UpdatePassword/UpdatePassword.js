import React, { Component } from "react";
import UserService from "../../../services/UserService";
import '../ChangePersonalInfo/ChangePersonalInfo.css'
import { FaEllipsisH } from 'react-icons/fa';
import AuthenticationService from "../../../services/AuthenticationService";
import imgAdmin from "../../../img/user.png"


export default class UpdatePassword extends Component {
    constructor(props) {
        super(props);

        this.handleSubmitUser = this.handleSubmitUser.bind(this);
        this.handleSubmitAdmin = this.handleSubmitAdmin.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
        this.onChangeConfirmPassword = this.onChangeConfirmPassword.bind(this);
        this.onChangePasswordAdmin = this.onChangePasswordAdmin.bind(this);
        this.onChangeConfirmPasswordAdmin = this.onChangeConfirmPasswordAdmin.bind(this);

        this.state = {
            currentUser: AuthenticationService.getCurrentUser(),
            currentAdmin: AuthenticationService.getCurrentAdmin(),
            password: "",
            confirmPassword: "",
            passwordAdmin: "",
            confirmPasswordAdmin: "",
            message: "",
            loading: false,
            valid: false,
            submitted: false
        };
    }

    onChangePassword(e) {
        this.setState({
            password: e.target.value
        });
    }

    onChangeConfirmPassword(e) {
        this.setState({
            confirmPassword: e.target.value
        });
    }

    onChangePasswordAdmin(e) {
        this.setState({
            passwordAdmin: e.target.value
        });
    }

    onChangeConfirmPasswordAdmin(e) {
        this.setState({
            confirmPasswordAdmin: e.target.value
        });
    }

    handleSubmitUser = (event) => {
        event.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        if (this.state.password && this.state.confirmPassword) {
            this.setState({
                valid: true
            });
            UserService.updatePassword(this.state.currentUser.username, this.state.password, this.state.confirmPassword, true).
                then(
                    () => {
                        this.setState({
                            message: "Your password was succesfully updated!"
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

        if (this.state.passwordAdmin && this.state.confirmPasswordAdmin) {
            this.setState({
                valid: true
            });
            UserService.updatePassword(this.state.currentAdmin.username, this.state.passwordAdmin, this.state.confirmPasswordAdmin, false).
                then(
                    () => {
                        this.setState({
                            message: "Your password was succesfully updated!"
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
                                <h3>Update Your Password</h3>
                                <form className="update-personal-info" onSubmit={this.handleSubmitAdmin}>
                                    {this.state.submitted && this.state.valid && this.state.message !== "" ? <div className="success-message-updated">{this.state.message}</div> : null}
                                    <div className="data">
                                        <h4>New Password</h4>
                                        <input
                                            onChange={this.onChangePasswordAdmin}
                                            value={this.state.passwordAdmin}
                                            id="password"
                                            className="form-field"
                                            type="password"
                                            name="password"
                                        />
                                    </div>
                                    {this.state.submitted && !this.state.passwordAdmin ? <span className="error" id="password-error">Please enter a new password</span> : null}
                                    <div className="data">
                                        <h4>Confirm New Password</h4>
                                        <input
                                            onChange={this.onChangeConfirmPasswordAdmin}
                                            value={this.state.confirmPasswordAdmin}
                                            id="confirmPassword"
                                            className="form-field"
                                            type="password"
                                            name="confirmPassword"
                                        />
                                    </div>
                                    {this.state.submitted && !this.state.confirmPasswordAdmin ? <span className="error" id="confirmPassword-error">Please confirm the new password</span> : null}
                                    <button className="btn-update-info">Save your new password
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
                            <h3>Update Your Password</h3>
                            <form className="update-personal-info" onSubmit={this.handleSubmitUser}>
                                {this.state.submitted && this.state.valid && this.state.message !== "" ? <div className="success-message-updated">{this.state.message}</div> : null}
                                <div className="data">
                                    <h4>New Password</h4>
                                    <input
                                        onChange={this.onChangePassword}
                                        value={this.state.password}
                                        id="password"
                                        className="form-field"
                                        type="password"
                                        name="password"
                                    />
                                </div>
                                {this.state.submitted && !this.state.password ? <span className="error" id="password-error">Please enter a new password</span> : null}
                                <div className="data">
                                    <h4>Confirm New Password</h4>
                                    <input
                                        onChange={this.onChangeConfirmPassword}
                                        value={this.state.confirmPassword}
                                        id="confirmPassword"
                                        className="form-field"
                                        type="password"
                                        name="confirmPassword"
                                    />
                                </div>
                                {this.state.submitted && !this.state.confirmPassword ? <span className="error" id="confirmPassword-error">Please confirm the new password</span> : null}
                                <button className="btn-update-info">Save your new password
                                    <span className="dots-explore-hotels"><FaEllipsisH /></span></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}