import React, { useState, useEffect } from 'react'
import './Messages.css'
import MessageService from '../../../../services/MessageService'
import Message from './Message';
import AuthenticationService from '../../../../services/AuthenticationService';
import { Redirect } from 'react-router-dom';

function Messages() {
    const [allMessages, setAllMessages] = useState([]);
    const [notAnsweredMessages, setNotAnsweredMessages] = useState([]);
    const [messagesToShow, setMessagesToShow] = useState([]);
    const [currentAdmin, setCurrentAdmin] = useState(Object)

    useEffect(() => {
        setCurrentAdmin(AuthenticationService.getCurrentAdmin())
        MessageService.getAllMessages(setAllMessages);
        MessageService.getNotAnsweredMessages(setNotAnsweredMessages);
        MessageService.getNotAnsweredMessages(setMessagesToShow);
    }, [])


    function showAllMessages() {
        setMessagesToShow(allMessages);
    }

    function showNotAnsweredMessages() {
        setMessagesToShow(notAnsweredMessages);
    }


    return (
        <div>
            {currentAdmin ?
                <div className='messages-inbox'>
                    <div className="menu">
                        <button className='messagesinboxunread' onClick={showNotAnsweredMessages}>Not Answered</button>
                        <button className='messages-inbox-all' onClick={showAllMessages}>All Messages</button>
                    </div>
                    <table className="table table-inbox table-hover">
                        <tbody>
                            {messagesToShow.map(message => (
                                <Message sender={message.senderName} email={message.senderEmail} content={message.content}
                                    id={message.id} status={message.status} />
                            )
                            )}
                        </tbody>
                    </table>
                </div>
                :
                <Redirect to="/admin" />
            }
        </div>
    )
}

export default Messages
