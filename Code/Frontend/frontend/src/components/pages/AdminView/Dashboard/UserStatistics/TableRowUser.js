import React from 'react'

function TableRowUser(props) {
    return (
        <div class="table-row">
            <div class="table-data">{props.name}</div>
            <div class="table-data">{props.username}</div>
            <div class="table-data">{props.email}</div>
            <div class="table-data">{props.joinDate}</div>
        </div>
    )
}

export default TableRowUser
