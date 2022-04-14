package com.example.demo.controller;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.HotelApplication;
import com.example.demo.serviceInterfaces.IHotelApplicationService;
import com.example.demo.serviceInterfaces.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/hotelApplication")
public class HotelApplicationController {

    @Autowired
    private IHotelApplicationService iHotelApplicationService;

    @Autowired
    private IHotelService iHotelService;


    public HotelApplicationController(IHotelApplicationService iHotelApplicationService, IHotelService iHotelService) {
        this.iHotelApplicationService = iHotelApplicationService;
        this.iHotelService = iHotelService;
    }

    @GetMapping("{id}")
    public ResponseEntity<HotelApplication> getHotelApplicationById(@PathVariable(value = "id") Long id) {
        HotelApplication hotelApplication = iHotelApplicationService.getHotelApplicationById(id);

        if (hotelApplication != null) {
            return new ResponseEntity<>(hotelApplication, OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<HotelApplication>> getAllHotelApplications() {
        List<HotelApplication> hotelApplications = iHotelApplicationService.getAllHotelApplications();
        return new ResponseEntity<>(hotelApplications, OK);
    }

    @PostMapping("/addHotelApplication")
    public ResponseEntity<HotelApplication> addHotelApplication(@RequestBody HotelApplication hotelApplication) {
        return new ResponseEntity<>(iHotelApplicationService.addHotelApplication(hotelApplication), OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<HotelApplication>> getHotelApplicationsOfUser(@PathParam(value = "userId") Long userId) {
        List<HotelApplication> hotelApplications = iHotelApplicationService.getHotelApplicationsOfUser(userId);
        return new ResponseEntity<>(hotelApplications, OK);
    }

    @PostMapping("/acceptHotelApplication")
    public ResponseEntity<String> acceptHotelApplication(@PathParam(value = "id") Long id) {
        try
        {
            iHotelApplicationService.acceptApplication(id);
            HotelApplication ha = iHotelApplicationService.getHotelApplicationById(id);
            Hotel newHotel = new Hotel(ha.getName(), ha.getCity(), ha.getCountry(), ha.getAddress(), ha.getRating(), ha.getPhoneNumber(),
                    ha.getFreeParking(), ha.getFreeCancellation(), ha.getTopLocation(), ha.getDescription(), ha.getFacilities(), ha.getUserId());
            iHotelService.addHotel(newHotel);
            return new ResponseEntity<>("The application was successfully accepted! The hotel will be displayed on the website.", OK);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), OK);

        }
    }

    @PostMapping("/declineHotelApplication")
    public ResponseEntity<String> declineHotelApplication(@PathParam(value = "id") Long id) {
        try
        {
            iHotelApplicationService.declineApplication(id);
            return new ResponseEntity<>("The application was successfully declined!", OK);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), OK);

        }
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<HotelApplication>> getAcceptedHotelApplicationsOfUser(@PathParam(value = "userId") Long userId) {
        return new ResponseEntity<>(iHotelApplicationService.getHotelApplicationsOfUserByStatus(userId, "ACCEPTED"), OK);
    }

    @GetMapping("/declined")
    public ResponseEntity<List<HotelApplication>> getDeclinedHotelApplicationsOfUser(@PathParam(value = "userId") Long userId) {
        return new ResponseEntity<>(iHotelApplicationService.getHotelApplicationsOfUserByStatus(userId, "DECLINED"), OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<HotelApplication>> getPendingHotelApplicationsOfUser(@PathParam(value = "userId") Long userId) {
        return new ResponseEntity<>(iHotelApplicationService.getHotelApplicationsOfUserByStatus(userId, "PENDING"), OK);
    }

    @GetMapping("/pendingApplications")
    public ResponseEntity<List<HotelApplication>> getAllPendingHotelApplications() {
        return new ResponseEntity<>(iHotelApplicationService.getHotelApplicationsByStatus("PENDING"), OK);
    }

    @GetMapping("/acceptedApplications")
    public ResponseEntity<List<HotelApplication>> getAllAcceptedHotelApplications() {
        return new ResponseEntity<>(iHotelApplicationService.getHotelApplicationsByStatus("ACCEPTED"), OK);
    }

    @GetMapping("/declinedApplications")
    public ResponseEntity<List<HotelApplication>> getAllDeclinedHotelApplications() {
        return new ResponseEntity<>(iHotelApplicationService.getHotelApplicationsByStatus("DECLINED"), OK);
    }

}
