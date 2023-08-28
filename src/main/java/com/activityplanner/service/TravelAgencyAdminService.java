package com.activityplanner.service;

import com.activityplanner.entity.TravelPackage;
import com.activityplanner.view.*;

import java.util.List;

public interface TravelAgencyAdminService {

    void addTravelAgencyAdmin(AddTravelAdminRequest addTravelAdminRequest);

    void addTravelPackage(AddTravelPackageRequest addTravelPackageRequest);

    void addItinerary(AddItineraryRequest addItineraryRequest);

    void addActivity(AddActivityRequest addActivityRequest);

    void addTravelDestination(AddTravelDestinationRequest addTravelDestinationRequest);


    List<TravelPackage> getAllTravelPackageDetails();


    TravelPackage getTravelPackageDetails(Long travelPackageId);

    AvailableActivitiesResponse getAvailableActivities(String destinationName);

    TravelPackagePassengerDetailsResponse getTravelPackagePassengerDetails(Long travelPackageId);

    TravelPackageItineraryDetailsResponse getTravelPackageItineraryDetails(Long travelPackageId);
}
