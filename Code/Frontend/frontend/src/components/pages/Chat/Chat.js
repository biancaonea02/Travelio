import React, { useState, useEffect } from 'react'
import './Chat.css'
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import AuthenticationService from '../../../services/AuthenticationService';

const ENDPOINT = "http://localhost:8080/ws";
function Chat() {

    const [stompClient, setStompClient] = useState(null);
    const [chatToDisplay, setChatToDisplay] = useState([]);
    const [msgToSend, setSendMessage] = useState({
        message: "",
        username: AuthenticationService.getCurrentAdmin().username
    });
    const chatMessages = new Array();

    function onConnect() {
        const socket = SockJS(ENDPOINT);
        const stompClient = Stomp.over(socket);
        stompClient.connect({}, () => {
            // subscribe to the backend
            stompClient.subscribe('/topic/greetings', (data) => {
                onMessageReceived(data);
                chatMessages.push(JSON.parse(data.body));
                setChatToDisplay(chatMessages);
            });
        });
        setStompClient(stompClient);
    }

    useEffect(() => {
        onConnect();
    }, []);


    // display the received data
    function onMessageReceived(data) {
        const result = JSON.parse(data.body);
    };

    function send() {
        stompClient.send("/app/chat", {}, JSON.stringify({ 'username': msgToSend.username, 'message': msgToSend.message }));
        chatMessages.push(msgToSend.message);
        setChatToDisplay(chatMessages);
    }
    console.log(chatToDisplay);

    return (
        <div className="home">

            <div className='chatbox'>
                {
                    chatToDisplay.map((item) => {
                        return (<div className='singleMessageContainer'>
                            <h4>{item.username}</h4>
                            <p key={item}>{item.message}</p>
                            {console.log(item.username)}
                        </div>);
                    })
                }
                <p>{chatMessages[0]}</p>
                <div className='send-message-container'>
                    <input className='your-message' onChange={(event) => setSendMessage({ ...msgToSend, message: event.target.value })} placeholder='Type your message here...'></input>

                    <button className='buttonSendChatMessage userAddButton' onClick={send}>Send Message</button>

                </div>
            </div>
        </div>
    )
}

export default Chat


