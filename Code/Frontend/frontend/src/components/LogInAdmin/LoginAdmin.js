import React, { Component } from "react";
import AuthenticationService from "../../services/AuthenticationService";
import '../LogInUser/LogIn.css'
import {withRouter } from "react-router-dom";


class LoginAdmin extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onChangeUsername = this.onChangeUsername.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);

        this.state = {
            username: "",
            password: "",
            loading: false,
            submitted: false,
            valid: false,
            message: "",
        };
    }


    onChangeUsername(e) {
        this.setState({
            username: e.target.value
        });
    }

    onChangePassword(e) {
        this.setState({
            password: e.target.value
        });
    }


    handleSubmit = (event) => {
        event.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        if (this.state.username && this.state.password) {
            this.setState({
                valid: true
            });

            if (AuthenticationService.loginAdmin(this.state.username, this.state.password) === false) {
                this.setState({
                    message: "You do not have the authority to log-in"
                });
            }

            AuthenticationService.loginAdmin(this.state.username, this.state.password)
                .then(
                    () => {
                        this.props.history.push("/admin/dashboard");
                        window.location.reload(false);
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
            < div className="log-in" >
                <div className="form-container">
                    <h1 className="greeting-log-in">Welcome to the admins' page!</h1>
                    <p className="paragraph-greeting-log-in">Please enter your credentials</p>
                    <form className="register-form" onSubmit={this.handleSubmit}>
                        {this.state.submitted && this.state.valid && this.state.message !== "" ? <div className="success-message">{this.state.message}</div> : null}
                        <input
                            onChange={this.onChangeUsername}
                            value={this.state.username}
                            id="username"
                            className="form-field"
                            type="text"
                            placeholder="Username*"
                            name="username"
                        />
                        {this.state.submitted && !this.state.username ? <span className="error" id="username-error">Please enter an username</span> : null}
                        <input
                            onChange={this.onChangePassword}
                            value={this.state.password}
                            id="pasword"
                            className="form-field"
                            type="password"
                            placeholder="Password*"
                            name="password"
                        />
                        {this.state.submitted && !this.state.password ? <span className="error" id="password-error">Please enter a password</span> : null}
                        <button className="form-field" id="btnSubmit" type="submit">
                            Log In
                        </button>
                    </form>
                </div>
            </div >
        );
    }
}

export default withRouter(LoginAdmin);
