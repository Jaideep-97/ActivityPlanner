package com.activityplanner.service;

import com.activityplanner.entity.Activity;
import com.activityplanner.entity.Balance;
import com.activityplanner.entity.TravelPackage;
import com.activityplanner.view.AddPassengerRequest;
import com.activityplanner.view.PassengerDetailsResponse;

import java.util.List;

public interface PassengerService {

    void addPassenger(AddPassengerRequest addPassengerRequest);

    PassengerDetailsResponse getPassengerDetails(Long userId);

    void addBalanceForPassenger(Long userId, Double balance);

    void saveTravelPackageForUser(Long userId, Long travelPackageId);

    void addActivityForPassenger(Long userId, String activityName);


    TravelPackage getTravelPackageForUser(Long userId, Long travelPackageId);

    List<TravelPackage> getAllTravelPackageForUser(Long userId);

    Double getBalanceForUser(Long userId);

    List<Activity> getAllActivitiesForUser(Long userId);

}
