import React from 'react'
import axios from 'axios'
import jwt from 'jwt-decode'

const API_URL = "http://localhost:8080/user"

class AuthenticationService {

    login(username, password) {
        return axios
            .post(API_URL + "/login", {
                username,
                password
            })
            .then(response => {
                if (response.data) {
                    var loggedInUser =
                    {
                        id: response.data.id,
                        firstName: response.data.firstName,
                        lastName: response.data.lastName,
                        username: response.data.username,
                        email: response.data.email,
                        joinDate: response.data.joinDate,
                        role: response.data.role
                    }
                    localStorage.setItem("user", JSON.stringify(loggedInUser));
                    localStorage.setItem("user-token", JSON.stringify(response.headers["jwt-token"]));
                }

                return response.data;
            });

    }

    loginAdmin(username, password) {
        return axios
            .post(API_URL + "/login", {
                username,
                password
            })
            .then(response => {
                if (response.data.role === "ROLE_ADMIN") {
                    var loggedInAdmin =
                    {
                        id: response.data.id,
                        firstName: response.data.firstName,
                        lastName: response.data.lastName,
                        username: response.data.username,
                        email: response.data.email,
                        role: response.data.role,
                        joinDate: response.data.joinDate,
                    }
                    localStorage.setItem("admin", JSON.stringify(loggedInAdmin));
                    localStorage.setItem("admin-token", JSON.stringify(response.headers["jwt-token"]));
                    return response.data;
                }
                else {
                    return false;
                }

            });

    }

    logout() {
        localStorage.removeItem("user");
        localStorage.removeItem("user-token");
    }

    logOutAdmin() {
        localStorage.removeItem("admin");
        localStorage.removeItem("admin-token");
    }

    register(firstName, lastName, username, email) {
        return axios.post(API_URL + "/register", {
            firstName,
            lastName,
            username,
            email
        })

    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem("user"));
    }

    getCurrentAdmin() {
        return JSON.parse(localStorage.getItem("admin"));
    }


    getCurrentUserToken() {
        return JSON.parse(localStorage.getItem("user-token"));
    }

    getCurrentAdminToken() {
        return JSON.parse(localStorage.getItem("admin-token"));
    }


    logoutWhenTokenExpires() {
        let token = localStorage.getItem('user-token')
        const decodedToken = jwt(token)
        const { exp } = decodedToken;
        if (exp * 1000 > new Date().getTime()) {
            this.logout();
        }
    }
}

export default new AuthenticationService();
