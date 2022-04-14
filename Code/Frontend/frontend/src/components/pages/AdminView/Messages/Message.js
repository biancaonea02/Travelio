import React from 'react'
import './Messages.css'
import { Link } from 'react-router-dom'
import { FaEllipsisH } from 'react-icons/fa'

function Message(props) {
    return (
        <tr className="unread">
            <td className="inbox-small-cells"><i className="fa fa-star"></i></td>
            <td className="view-message  dont-show">{props.sender}</td>
            <td className="view-message  dont-show">{props.email}</td>
            <td className="view-message ">{props.content}</td>
            <td className="view-message text-right">
                {props.status === "NOT ANSWERED" ?
                    <Link
                        to={{
                            pathname: "/admin/open-message",
                            state: { id: props.id }
                        }}
                        className="open-message"
                    >Open the message<span className="dots-open-message"><FaEllipsisH /></span></Link>
                    :
                    <h3 className='answered-message'>ANSWERED</h3>
                }
            </td>
        </tr>
    )
}

export default Message