package com.activityplanner.controller;

import com.activityplanner.entity.Activity;
import com.activityplanner.entity.TravelPackage;
import com.activityplanner.service.PassengerService;
import com.activityplanner.view.AddPassengerRequest;
import com.activityplanner.view.PassengerDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @RequestMapping(value = "/api/addPassenger", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> addPassenger(@RequestBody AddPassengerRequest addPassengerRequest) {

        passengerService.addPassenger(addPassengerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getPassengerDetails", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getPassengerDetails(@RequestParam Long userId) {

        PassengerDetailsResponse passengerDetailsResponse = passengerService.getPassengerDetails(userId);
        return new ResponseEntity<>(passengerDetailsResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/addBalanceForPassenger", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> addBalanceForPassenger(@RequestParam Long userId, @RequestParam Double balance) {

        passengerService.addBalanceForPassenger(userId, balance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/addTravelPackageForPassenger", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> addTravelPackageForPassenger(@RequestParam Long userId, @RequestParam Long travelPackageId) {

        passengerService.saveTravelPackageForUser(userId, travelPackageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/addActivityForPassenger", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> addActivityForPassenger(@RequestParam Long userId, @RequestParam String activityName) {

        passengerService.addActivityForPassenger(userId, activityName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getTravelPackageForPassenger", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getTravelPackageForPassenger(@RequestParam Long userId, @RequestParam Long travelPackageId) {

        TravelPackage travelPackage = passengerService.getTravelPackageForUser(userId, travelPackageId);
        return new ResponseEntity<>(travelPackage, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getAllTravelPackageForPassenger", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getAllTravelPackageForPassenger(@RequestParam Long userId) {

        List<TravelPackage> travelPackageList = passengerService.getAllTravelPackageForUser(userId);
        return new ResponseEntity<>(travelPackageList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getBalanceForPassenger", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getBalanceForPassenger(@RequestParam Long userId) {

        Double balance = passengerService.getBalanceForUser(userId);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/getAllActivitiesForPassenger", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getAllActivitiesForUser(@RequestParam Long userId) {

        List<Activity> activityList = passengerService.getAllActivitiesForUser(userId);
        return new ResponseEntity<>(activityList, HttpStatus.OK);
    }
}
