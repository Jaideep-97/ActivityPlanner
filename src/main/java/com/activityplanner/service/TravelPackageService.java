package com.activityplanner.service;

import com.activityplanner.entity.TravelPackage;
import com.activityplanner.view.AddTravelPackageRequest;
import com.activityplanner.view.TravelPackageUpdateRequest;

import java.util.List;

public interface TravelPackageService {

    TravelPackage getTravelPackageDetails(Long travelPackageId);

    void addTravelPackage(AddTravelPackageRequest addTravelPackageRequest);

    void addPassengerInTravelPackage(Long travelPackageId, String passengerName);

    void modifyTravelPackage(Long travelPackageId, TravelPackageUpdateRequest travelPackageUpdateRequest);

    List<TravelPackage> getAllTravelPackageForUser(String passengerName);

    List<TravelPackage> getAllTravelPackage();
}
