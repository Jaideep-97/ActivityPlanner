package com.activityplanner.service.impl;

import com.activityplanner.dao.PassengerDao;
import com.activityplanner.entity.*;
import com.activityplanner.service.*;
import com.activityplanner.view.AddPassengerRequest;
import com.activityplanner.view.PassengerDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private TravelPackageService travelPackageService;

    @Autowired
    private ActivityService activityService;


    @Autowired
    private PassengerDao passengerDao;

    @Autowired
    private BalanceService balanceService;


    @Override
    public void addPassenger(AddPassengerRequest addPassengerRequest) {

        Passenger passenger = new Passenger();
        passenger.setName(addPassengerRequest.getName());
        passenger.setPassengerType(addPassengerRequest.getPassengerType());
        passengerDao.save(passenger);
    }

    @Override
    public PassengerDetailsResponse getPassengerDetails(Long userId) {
        Passenger passenger = passengerDao.findOne(userId);
        PassengerDetailsResponse passengerDetailsResponse = new PassengerDetailsResponse();
        if(passenger != null) {
            passengerDetailsResponse.setName(passenger.getName());
            passengerDetailsResponse.setPassengerNumber(passenger.getId());
            passengerDetailsResponse.setBalance(balanceService.getBalanceForUser(userId));
            List<Activity> activityList = activityService.getActivitiesForPassenger(userId);
            passengerDetailsResponse.setActivityList(activityList);
        }
        return passengerDetailsResponse;
    }


    @Override
    public void addBalanceForPassenger(Long userId, Double balance) {

        Double balance1 = balanceService.getBalanceForUser(userId);
        if(balance1 != null)
            balance1 += balance1 + balance;
        else
            balance1 = balance;
        Balance b = new Balance();
        b.setBalanceLeft(balance1);
        b.setPassengerId(userId);
        balanceService.addBalance(b);
    }

    @Override
    public void saveTravelPackageForUser(Long userId, Long travelPackageId) {

        Passenger passenger = passengerDao.findOne(userId);
        if(passenger == null)
            return;
        travelPackageService.addPassengerInTravelPackage(travelPackageId, passenger.getName());
    }

    @Override
    public void addActivityForPassenger(Long userId, String activityName) {
        Activity activity = activityService.getActivityByName(activityName);
        Passenger passenger = passengerDao.findOne(userId);
        if(activity == null || passenger == null)
            return;
        if(!activityService.isActivityFull(activityName)) {
            if(Objects.equals(passenger.getPassengerType(), PassengerType.STANDARD.name()) && balanceService.getBalanceForUser(userId)>activity.getCost()) {
                activityService.bookActivityForPassenger(activityName, userId);
                BalanceStrategyContext balanceStrategyContext = new BalanceStrategyContext(new BalanceCalcForStandardPassengers());
                balanceStrategyContext.doOperation(userId, activityName);
            }
            else if(Objects.equals(passenger.getPassengerType(), PassengerType.GOLD.name()) && balanceService.getBalanceForUser(userId)> 0.9*activity.getCost()) {
                activityService.bookActivityForPassenger(activityName, userId);
                BalanceStrategyContext balanceStrategyContext = new BalanceStrategyContext(new BalanceStrategyForGoldPassengers());
                balanceStrategyContext.doOperation(userId, activityName);
            }
        }
    }


    @Override
    public TravelPackage getTravelPackageForUser(Long userId, Long travelPackageId) {
        TravelPackage travelPackage = travelPackageService.getTravelPackageDetails(travelPackageId);
        if(travelPackage == null)
            return null;
        Long[] passengerListIds = travelPackage.getPassengerListIds();
        for(Long passengerId : passengerListIds) {
            if(Objects.equals(passengerId, userId)) {
                return travelPackage;
            }
        }
        return null;
    }

    @Override
    public List<TravelPackage> getAllTravelPackageForUser(Long userId) {
        Passenger passenger = passengerDao.findOne(userId);
        if(passenger == null)
            return new ArrayList<>();
        return travelPackageService.getAllTravelPackageForUser(passenger.getName());
    }

    @Override
    public Double getBalanceForUser(Long userId) {
        return balanceService.getBalanceForUser(userId);
    }

    @Override
    public List<Activity> getAllActivitiesForUser(Long userId) {
        return activityService.getActivitiesForPassenger(userId);
    }
}
