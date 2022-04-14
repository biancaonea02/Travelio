import React, { Component } from 'react'
import IntroSection from './IntroSection/IntroSection';
import Booking from './Booking/Booking'
import ExploreHotels from './ExploreHotels/ExploreHotels';
import CityDestinations from './CityDestination/CityDestinationGrid';
import Contact from './Contact/Contact';
import CreateAccount from './CreateAccountSection/CreateAccount';
import AuthenticationService from '../../../services/AuthenticationService';


export default class Home extends Component {
  constructor(props) {
    super(props);

    this.state = {
      currentUser: AuthenticationService.getCurrentUser(),
    };
  }

  render() {
    return (
      <>
        <IntroSection />
        {this.state.currentUser ? <Booking /> : null}
        <ExploreHotels />
        {this.state.currentUser ? null : <CreateAccount />}
        <CityDestinations />
        <Contact />
      </>

    );
  }
}
