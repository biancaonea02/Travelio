import axios from 'axios';
import AuthenticationService from './AuthenticationService';

const API_URL = 'http://localhost:8080/message';

class MessageService {
    sendMessage(senderName, senderEmail, content, status) {
        let body = {
            senderName: senderName,
            senderEmail: senderEmail,
            content: content,
            status: status
        }
        return axios.post(API_URL + "/addMessage", body)
            .then(response => {
                return response.data
            });
    }

    getMessageById(id, setMessage) {
        return axios({
            method: 'get',
            url: API_URL + `/${id}`,
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
        })
            .then(response => {
                setMessage(response.data)
            });
    }

    getAllMessages(setMessages) {
        return axios({
            method: 'get',
            url: API_URL,
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
        })
            .then(response => {
                setMessages(response.data)
            });
    }

    getNotAnsweredMessages(setMessages) {
        return axios({
            method: 'get',
            url: API_URL + '/notAnswered',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
        })
            .then(response => {
                setMessages(response.data)
            });
    }

    sendMessageResponse(id, name, subject, content, email) {
        return axios({
            method: 'post',
            url: API_URL + '/sendMessageResponse',
            headers: { 'Authorization': 'Bearer ' + AuthenticationService.getCurrentAdminToken() },
            params: {
                id: id,
                name: name,
                subject: subject,
                content: content,
                email: email
            }
        })
            .then(response => {
                return response.data
            });
    }

}

export default new MessageService();