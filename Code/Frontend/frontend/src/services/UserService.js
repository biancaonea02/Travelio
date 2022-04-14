import axios from 'axios';
import AuthenticationService from './AuthenticationService';

const API_URL = 'http://localhost:8080/user';

class UserService {

    getUserById(id, setUser) {
        return axios({
            method: 'get',
            url: API_URL + `/${id}`,
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
        })
            .then(response => {
                setUser(response.data)
            });
    }

    updatePersonalInformation(currentUsername, newUsername, newEmail, isUser) {
        if (isUser === true) {
            return axios({
                method: 'post',
                url: API_URL + "/updatePersonalInfo",
                headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
                params: {
                    currentUsername: currentUsername, // This is the body part
                    username: newUsername,
                    email: newEmail
                }
            })
                .then(response => {
                    if (response.data) {

                        var loggedInUser =
                        {
                            firstName: response.data.firstName,
                            lastName: response.data.lastName,
                            username: response.data.username,
                            email: response.data.email,
                            joinDate: response.data.joinDate,
                        }
                        localStorage.setItem("user", JSON.stringify(loggedInUser));
                    }

                    return response.data
                });
        }
        else {
            return axios({
                method: 'post',
                url: API_URL + "/updatePersonalInfo",
                headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
                params: {
                    currentUsername: currentUsername, // This is the body part
                    username: newUsername,
                    email: newEmail
                }
            })
                .then(response => {
                    if (response.data) {

                        var loggedInAdmin =
                        {
                            firstName: response.data.firstName,
                            lastName: response.data.lastName,
                            username: response.data.username,
                            email: response.data.email,
                            joinDate: response.data.joinDate,
                        }
                        localStorage.setItem("admin", JSON.stringify(loggedInAdmin));
                    }

                    return response.data
                });
        }
    }

    resetPassword(userEmail) {
        return axios({
            method: 'get',
            url: API_URL + `/resetPassword/${userEmail}`
        })
            .then(response => {
                if (response.data) {
                    return response.data
                }
            });
    }

    updatePassword(username, newPassword, confirmNewPassword, isUser) {
        if (isUser === true) {
            return axios({
                method: 'post',
                url: API_URL + "/changePassword",
                headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentUserToken() },
                params: {
                    username: username,
                    password: newPassword,
                    confirmPassword: confirmNewPassword
                }
            })
                .then(response => {
                    if (response.data) {
                        return response.data
                    }
                });
        }
        else {
            return axios({
                method: 'post',
                url: API_URL + "/changePassword",
                headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
                params: {
                    username: username,
                    password: newPassword,
                    confirmPassword: confirmNewPassword
                }
            })
                .then(response => {
                    if (response.data) {
                        return response.data
                    }
                });
        }
    }

    getAllUsers(setUsers) {
        axios.get(API_URL)
            .then(res => {
                setUsers(res.data)
            })
    }

    getLastFiveUsers(setLastUsers) {
        return axios({
            method: 'get',
            url: API_URL + '/getLastUsers',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() }
        })
            .then(response => {
                if (response.data) {
                    setLastUsers(response.data)
                }
            });
    }
}

export default new UserService();