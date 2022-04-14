import React, { useState, useEffect } from 'react'
import { useLocation } from 'react-router-dom';
import MessageService from '../../../../services/MessageService';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import './Messages.css'
import '../../../pages/HomePage/Contact/Contact.css'

function OpenMessage() {
    const location = useLocation();
    const { id } = location.state;
    const [message, setMessage] = useState(Object)
    const [email, setNewEmail] = useState({
        to: "",
        subject: "",
        content: ""
    });
    const [messageAction, setMessageAction] = useState();
    const [submitted, setSubmitted] = useState(false);
    const [valid, setValid] = useState(false);

    const handleSubjectInputChange = (event) => {
        setNewEmail({ ...email, subject: event.target.value })
    }

    const handleContentInputChange = (event) => {
        setNewEmail({ ...email, content: event.target.value })
    }


    useEffect(() => {
        MessageService.getMessageById(id, setMessage);
        setNewEmail({ ...email, to: message.senderEmail })
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();
        if (email.subject && email.content) {
            setValid(true);
            MessageService.sendMessageResponse(id, message.senderName, email.subject, email.content, message.senderEmail).
                then(
                    () => {
                        setMessageAction("Your response message was send! The user will receive the message on the e-mail.")
                    },
                    error => {
                        const resMessage =
                            (error.response &&
                                error.response.data &&
                                error.response.data.message) ||
                            error.message ||
                            error.toString();

                        setMessageAction(resMessage);
                    }
                );
        }
        setSubmitted(true);
    }


    return (
        <div className='open-message-info'>
            <Card className="message-card">
                <CardHeader
                    title={`Message sent by ${message.senderName}`}
                />
                <CardContent>
                    <Typography variant="body2">
                        E-mail: {message.senderEmail}
                    </Typography>
                    <br />
                    <Typography variant="body2">
                        Message:  {message.content}
                    </Typography>
                </CardContent>
            </Card>
            <div className='respond-to-message'>
                <h1 className='respond-to-message-banner'>Respond to message</h1>
                <form className='send-message-response' onSubmit={handleSubmit}>
                    <input
                        type="text"
                        name="to"
                        placeholder={`To: ${message.senderEmail}`}
                        readOnly
                    />
                    <input
                        onChange={handleSubjectInputChange}
                        value={email.subject}
                        type="text"
                        value={email.subject}
                        name="subject"
                        placeholder="Subject"
                    />
                    {submitted && !email.subject ? <span className="error" id="name-error">Please enter the subject of the e-mail</span> : null}
                    <textarea
                        onChange={handleContentInputChange}
                        value={email.content}
                        className="input"
                        cols="30"
                        rows="8"
                        placeholder="Write the content of the e-mail">
                    </textarea>
                    {submitted && !email.content ? <span className="error" id="name-error">Please enter the content of your e-mail</span> : null}
                    <button type="submit" className='submit-response-msg'>Send e-mail</button>
                    <p>{messageAction}</p>
                </form>
            </div>
        </div>
    )
}

export default OpenMessage
