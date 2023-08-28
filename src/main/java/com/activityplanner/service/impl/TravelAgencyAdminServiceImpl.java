package com.activityplanner.service.impl;

import com.activityplanner.dao.ActivityPassengerDao;
import com.activityplanner.dao.DestinationDao;
import com.activityplanner.dao.PassengerDao;
import com.activityplanner.dao.TravelAgencyAdminDao;
import com.activityplanner.entity.*;
import com.activityplanner.service.*;
import com.activityplanner.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelAgencyAdminServiceImpl implements TravelAgencyAdminService {

    @Autowired
    private DestinationDao destinationDao;

    @Autowired
    private TravelPackageService travelPackageService;

    @Autowired
    private TravelAgencyAdminDao travelAgencyAdminDao;

    @Autowired
    private ItineraryService itineraryService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private ActivityPassengerDao activityPassengerDao;

    @Autowired
    private PassengerDao passengerDao;

    @Override
    public void addTravelAgencyAdmin(AddTravelAdminRequest addTravelAdminRequest) {
        TravelAgencyAdmin travelAgencyAdmin = new TravelAgencyAdmin();
        travelAgencyAdmin.setName(addTravelAdminRequest.getName());
        travelAgencyAdminDao.save(travelAgencyAdmin);
    }

    @Override
    public void addTravelPackage(AddTravelPackageRequest addTravelPackageRequest) {

        travelPackageService.addTravelPackage(addTravelPackageRequest);
    }

    @Override
    public void addItinerary(AddItineraryRequest addItineraryRequest) {
        itineraryService.addItinerary(addItineraryRequest);
    }

    @Override
    public void addActivity(AddActivityRequest addActivityRequest) {
        activityService.addActivity(addActivityRequest);
    }

    @Override
    public void addTravelDestination(AddTravelDestinationRequest addTravelDestinationRequest) {

        List<Destination> destinationList = destinationDao.findByDestinationName(addTravelDestinationRequest.getDestinationName());
        if(destinationList == null || CollectionUtils.isEmpty(destinationList)) {
            Destination destination = new Destination();
            destination.setDestinationName(addTravelDestinationRequest.getDestinationName());
            destinationDao.save(destination);
        }
    }

    @Override
    public List<TravelPackage> getAllTravelPackageDetails() {

        return travelPackageService.getAllTravelPackage();
    }

    @Override
    public TravelPackage getTravelPackageDetails(Long travelPackageId) {


        return travelPackageService.getTravelPackageDetails(travelPackageId);
    }

    @Override
    public AvailableActivitiesResponse getAvailableActivities(String destinationName) {
        AvailableActivitiesResponse availableActivitiesResponse = new AvailableActivitiesResponse();
        List<AvailableActivity> availableActivityList = new ArrayList<>();
        List<Activity> activityList = destinationService.getActivityListForDestination(destinationName);
        if(activityList == null)
            return availableActivitiesResponse;
        for(Activity activity : activityList) {
            if(!activityService.isActivityFull(activity.getActivityName())) {
                AvailableActivity availableActivity = new AvailableActivity();
                int count = activityPassengerDao.findByActivityId(activity.getId()).size();
                availableActivity.setActivityName(activity.getActivityName());
                availableActivity.setCount(activity.getCapacity()-count);
                availableActivityList.add(availableActivity);
            }
        }
        availableActivitiesResponse.setDestinationName(destinationName);
        availableActivitiesResponse.setActivitiesList(availableActivityList);
        return availableActivitiesResponse;
    }

    @Override
    public TravelPackagePassengerDetailsResponse getTravelPackagePassengerDetails(Long travelPackageId) {

        TravelPackagePassengerDetailsResponse travelPackagePassengerDetailsResponse = new TravelPackagePassengerDetailsResponse();
        TravelPackage travelPackage = travelPackageService.getTravelPackageDetails(travelPackageId);
        if(travelPackage == null)
            return travelPackagePassengerDetailsResponse;
        travelPackagePassengerDetailsResponse.setTravelPackageName(travelPackage.getTravelPackageName());
        travelPackagePassengerDetailsResponse.setCapacity(travelPackage.getCapacity());
        if(travelPackage.getPassengerListIds() != null)
            travelPackagePassengerDetailsResponse.setNumberOfPassengers(travelPackage.getPassengerListIds().length);
        else
            travelPackagePassengerDetailsResponse.setNumberOfPassengers(travelPackage.getCapacity());
        Long []passengerListIds = travelPackage.getPassengerListIds();
        List<String> passengerList = new ArrayList<>();
        if(passengerListIds != null) {
            for (Long passengerId : passengerListIds) {
                Passenger passenger = passengerDao.getOne(passengerId);
                if (passenger != null)
                    passengerList.add(passenger.getName());
            }
        }
        travelPackagePassengerDetailsResponse.setPassengerList(passengerList);
        return travelPackagePassengerDetailsResponse;
    }

    @Override
    public TravelPackageItineraryDetailsResponse getTravelPackageItineraryDetails(Long travelPackageId) {

        TravelPackageItineraryDetailsResponse travelPackageItineraryDetailsResponse = new TravelPackageItineraryDetailsResponse();
        TravelPackage travelPackage = travelPackageService.getTravelPackageDetails(travelPackageId);
        if(travelPackage == null)
            return travelPackageItineraryDetailsResponse;
        travelPackageItineraryDetailsResponse.setTravelPackageName(travelPackage.getTravelPackageName());
        List<Destination> destinationList = itineraryService.getAllDestinationForItinerary(travelPackage.getItineraryName());
        List<DestinationDetails> destinationDetailsList = new ArrayList<>();
        if(destinationList != null) {
            for(Destination destination : destinationList) {
                List<Activity> activityList = new ArrayList<>();
                DestinationDetails destinationDetails = new DestinationDetails();
                destinationDetails.setDestinationName(destination.getDestinationName());
                Long []activityIds = destination.getActivityListIds();
                if(activityIds == null)
                    continue;
                for(Long activityId : activityIds) {
                    Activity activity = activityService.getActivityById(activityId);
                    if(activity != null)
                        activityList.add(activity);
                }
                destinationDetails.setActivityList(activityList);
                destinationDetailsList.add(destinationDetails);
            }

        }
        travelPackageItineraryDetailsResponse.setDestinationList(destinationDetailsList);
        return travelPackageItineraryDetailsResponse;
    }
}
