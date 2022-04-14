import React, { useState, useEffect } from 'react'
import TableRowUser from './TableRowUser';
import UserService from '../../../../../services/UserService';

function UserStatistics() {

    const [lastUsers, setLastUsers] = useState([])

    useEffect(() => {
        UserService.getLastFiveUsers(setLastUsers);
    }, [])


    return (
        <div class="user-statistics-wrapper">
            <div class="panel-user-statistics">
                <div class="panel-header">
                    <h3 class="title">Last users that joined the website</h3>
                </div>

                <div class="panel-body">
                    <div class="user-statistics-container">
                        <div class="table">
                            <div class="table-header">
                                <div class="header__item"><a id="name" class="filter__link">Name</a></div>
                                <div class="header__item"><a id="wins" class="filter__link filter__link--number">Username</a></div>
                                <div class="header__item"><a id="draws" class="filter__link filter__link--number">E-mail</a></div>
                                <div class="header__item"><a id="losses" class="filter__link filter__link--number">Join Date</a></div>
                            </div>
                            <div class="table-content">
                                {lastUsers.map(user => (
                                    <TableRowUser name={user.firstName + " " + user.lastName} username={user.username} email={user.email} joinDate={user.joinDate} />
                                )
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default UserStatistics
