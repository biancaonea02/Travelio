import React, { Component } from 'react'
import Home from './components/pages/HomePage/Home';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import './App.css'
import SignUp from './components/Sign-Up/SignUp';
import Footer from './components/Footer/Footer';
import AllHotelsProba from './components/pages/AllHotels/AllHotelsProba';
import SearchResult from './components/pages/SearchResult/SearchResult';
import HotelDetails from './components/pages/HotelPage/HotelDetails';
import Dashboard from './components/pages/AdminView/Dashboard/Dashboard';
import ProfilePage from './components/pages/MyProfile/ProfilePage'
import ChangePersonalInfo from './components/pages/ChangePersonalInfo/ChangePersonalInfo'
import ResetPassword from './components/pages/ResetPassword/ResetPassword';

import AuthenticationService from './services/AuthenticationService';

import Login from './components/LogInUser/Login'
import UpdatePassword from './components/pages/UpdatePassword/UpdatePassword';
import LoginAdmin from './components/LogInAdmin/LoginAdmin';
import NavbarUser from './components/navbar/NavbarUser';
import NavbarAdmin from './components/navbar/NavbarAdmin';
import Messages from './components/pages/AdminView/Messages/Messages';
import Reserve from './components/pages/Reserve/Reserve';
import HotelActivity from './components/pages/HotelActivity/HotelActivity';
import HotelApplicationForm from './components/pages/HotelActivity/HotelApplicationForm';
import Applications from './components/pages/AdminView/Applications/Applications';
import Chat from './components/pages/Chat/Chat';
import ApplicationDetails from './components/pages/AdminView/ApplicationDetails/ApplicationDetails';
import OpenMessage from './components/pages/AdminView/Messages/OpenMessage';
import UpdateHotel from './components/pages/UpdateHotel/UpdateHotel';
import ManageReservations from './components/ManageReservations/ManageReservations';


export default class App extends Component {

  constructor(props) {
    super(props);
    this.logOutUser = this.logOutUser.bind(this);
    this.logOutAdmin = this.logOutAdmin.bind(this);
    this.logOutUserWhenTokenExpires = this.logOutUserWhenTokenExpires.bind(this);

    this.state = {
      currentUser: undefined,
      currentAdmin: undefined,
      isUserLoggedIn: false
    };
  }

  componentDidMount() {
    const user = AuthenticationService.getCurrentUser();
    const admin = AuthenticationService.getCurrentAdmin();

    if (user) {
      this.setState({
        currentUser: user
      });
    }

    if (admin) {
      this.setState({
        currentAdmin: admin
      });
    }
  }

  logOutUser() {
    AuthenticationService.logout();
  }

  logOutAdmin() {
    AuthenticationService.logOutAdmin();
  }

  logOutUserWhenTokenExpires() {
    AuthenticationService.logoutWhenTokenExpires();
  }


  render() {
    const path = window.location.pathname;
    if (path.startsWith("/admin")) {
      return <>
        <Router>
          <NavbarAdmin currentAdmin={this.state.currentAdmin} logOut={this.logOutAdmin} />
          <Switch>
            <Route exact path="/admin"><LoginAdmin /></Route>

            {this.state.currentAdmin ? <Route path="/admin/dashboard"><Dashboard /></Route> : <Route path="/admin"><LoginAdmin /></Route>}

            {this.state.currentAdmin ? <Route path="/admin/messages"><Messages /></Route> : <Route path="/admin"><LoginAdmin /></Route>}

            {this.state.currentAdmin ? <Route path="/admin/open-message"><OpenMessage /></Route> : <Route path="/admin"><LoginAdmin /></Route>}

            {this.state.currentAdmin ? <Route path="/admin/hotel-applications"><Applications /></Route> : <Route path="/admin"><LoginAdmin /></Route>}

            {this.state.currentAdmin ? <Route path="/admin/hotel-applications/application-details"><ApplicationDetails /></Route> : <Route path="/admin"><LoginAdmin /></Route>}

            {this.state.currentAdmin ? <Route path="/admin/chat"><Chat /></Route> : <Route path="/admin"><LoginAdmin /></Route>}

            {this.state.currentAdmin ? <Route path="/admin/profile/change-personal-info"><ChangePersonalInfo /></Route> : <Route path="/admin"><LoginAdmin /></Route>}

            {this.state.currentAdmin ? <Route path="/admin/profile/update-password"><UpdatePassword /></Route> : <Route path="/admin"><LoginAdmin /></Route>}

            {this.state.currentAdmin ? <Route path="/admin/profile"><ProfilePage /></Route> : <Route path="/admin"><LoginAdmin /></Route>}

            {this.state.currentAdmin ? <Route path="/admin/hotel-application"><ApplicationDetails /></Route> : <Route path="/admin"><LoginAdmin /></Route>}
          </Switch>
          <Footer />
        </Router>
      </>;
    }
    else {
      return <Router>
        <NavbarUser currentUser={this.state.currentUser} logOut={this.logOutUser} />
        <Switch>
          <Route exact path={["/", "/home"]} component={Home} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/sign-up" component={SignUp} />
          <Route exact path="/profile" exact component={ProfilePage} />
          <Route exact path="/profile/change-personal-info" exact component={ChangePersonalInfo} />
          <Route exact path="/reset-password" exact component={ResetPassword} />
          <Route exact path="/profile/update-password" exact component={UpdatePassword} />
          <Route path="/search-result" exact component={SearchResult} />
          <Route path="/all-hotels/:name" exact component={HotelDetails} />
          <Route path='/all-hotels' exact component={AllHotelsProba} />
          <Route path="/all-hotels/:name/reserve" exact component={Reserve} />
          <Route path="/hotel-activity" exact component={HotelActivity} />
          <Route path="/hotel-activity/start-new-form" exact component={HotelApplicationForm} />
          <Route path="/update-hotel/:id" exact component={UpdateHotel} />
          <Route path="/my-reservations" exact component={ManageReservations} />
        </Switch>
        <Footer />
      </Router>;
    }
  }
}


