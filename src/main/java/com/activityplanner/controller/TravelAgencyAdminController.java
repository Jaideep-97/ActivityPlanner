package com.activityplanner.controller;

import com.activityplanner.entity.TravelPackage;
import com.activityplanner.service.TravelAgencyAdminService;
import com.activityplanner.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TravelAgencyAdminController {

    @Autowired
    private TravelAgencyAdminService travelAgencyAdminService;


    @RequestMapping(value = "/api/addTravelAgencyAdmin", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> addTravelAgencyAdmin(@RequestBody AddTravelAdminRequest addTravelAdminRequest) {

        travelAgencyAdminService.addTravelAgencyAdmin(addTravelAdminRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/addTravelPackage", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> addTravelPackage(@RequestBody AddTravelPackageRequest addTravelPackageRequest) {

        travelAgencyAdminService.addTravelPackage(addTravelPackageRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/addItinerary", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> addItinerary(@RequestBody AddItineraryRequest addItineraryRequest) {

        travelAgencyAdminService.addItinerary(addItineraryRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/addActivity", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> addActivity(@RequestBody AddActivityRequest addActivityRequest) {

        travelAgencyAdminService.addActivity(addActivityRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/addTravelDestination", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> addTravelDestination(@RequestBody AddTravelDestinationRequest addTravelDestinationRequest) {

        travelAgencyAdminService.addTravelDestination(addTravelDestinationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getAllTravelPackage", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getAllTravelPackageDetails() {

        List<TravelPackage> travelPackageList = travelAgencyAdminService.getAllTravelPackageDetails();
        return new ResponseEntity<>(travelPackageList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getTravelPackage", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getAllTravelPackageDetails(@RequestParam Long travelPackageId) {

        TravelPackage travelPackage = travelAgencyAdminService.getTravelPackageDetails(travelPackageId);
        return new ResponseEntity<>(travelPackage, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getAvailableActivities", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<AvailableActivitiesResponse> getAvailableActivities(@RequestParam String destinationName) {

        AvailableActivitiesResponse availableActivitiesResponse = travelAgencyAdminService.getAvailableActivities(destinationName);
        return new ResponseEntity<>(availableActivitiesResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getTravelPackagePassengerDetails", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<TravelPackagePassengerDetailsResponse> getTravelPackagePassengerDetails(@RequestParam Long travelPackageId) {

        TravelPackagePassengerDetailsResponse travelPackagePassengerDetailsResponse = travelAgencyAdminService.getTravelPackagePassengerDetails(travelPackageId);
        return new ResponseEntity<>(travelPackagePassengerDetailsResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getTravelPackageItineraryDetails", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<TravelPackageItineraryDetailsResponse> getTravelPackageItineraryDetails(@RequestParam Long travelPackageId) {

        TravelPackageItineraryDetailsResponse travelPackageItineraryDetailsResponse = travelAgencyAdminService.getTravelPackageItineraryDetails(travelPackageId);
        return new ResponseEntity<>(travelPackageItineraryDetailsResponse, HttpStatus.OK);
    }
}
