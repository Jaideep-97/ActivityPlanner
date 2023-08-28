package com.activityplanner.service.impl;

import com.activityplanner.dao.PassengerDao;
import com.activityplanner.dao.TravelPackageDao;
import com.activityplanner.entity.Passenger;
import com.activityplanner.entity.TravelPackage;
import com.activityplanner.service.TravelPackageService;
import com.activityplanner.view.AddTravelPackageRequest;
import com.activityplanner.view.TravelPackageUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {

    @Autowired
    private TravelPackageDao travelPackageDao;

    @Autowired
    private PassengerDao passengerDao;


    @Override
    public TravelPackage getTravelPackageDetails(Long travelPackageId) {

        return travelPackageDao.findOne(travelPackageId);
    }

    @Override
    public void addTravelPackage(AddTravelPackageRequest addTravelPackageRequest) {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setTravelPackageName(addTravelPackageRequest.getTravelPackageName());
        travelPackage.setItineraryName(addTravelPackageRequest.getItineraryName());
        travelPackage.setTravelStartDate(LocalDate.now());
        travelPackage.setTravelEndDate(LocalDate.now());
        travelPackage.setCapacity(addTravelPackageRequest.getCapacity());
        travelPackageDao.save(travelPackage);
    }

    @Override
    public void addPassengerInTravelPackage(Long travelPackageId, String passengerName) {

        TravelPackage travelPackage = travelPackageDao.findOne(travelPackageId);
        List<Long> passengerListIds = new ArrayList<>();
        if(travelPackage != null) {
            if (travelPackage.getPassengerListIds() != null)
                passengerListIds = Arrays.asList(travelPackage.getPassengerListIds());
            List<Passenger> passengerList = passengerDao.findByName(passengerName);
            if(passengerList != null && !CollectionUtils.isEmpty(passengerList))
                passengerListIds.add(passengerList.get(0).getId());
            travelPackage.setPassengerListIds(passengerListIds.toArray(new Long[0]));
            travelPackageDao.save(travelPackage);
        }
    }

    @Override
    public void modifyTravelPackage(Long travelPackageId, TravelPackageUpdateRequest travelPackageUpdateRequest) {

        TravelPackage travelPackage = travelPackageDao.findOne(travelPackageId);
        travelPackage.setTravelStartDate(travelPackageUpdateRequest.getTravelStartDate());
        travelPackage.setTravelEndDate(travelPackageUpdateRequest.getTravelEndDate());
        travelPackage.setCapacity(travelPackage.getCapacity());
        travelPackageDao.save(travelPackage);

    }

    @Override
    public List<TravelPackage> getAllTravelPackageForUser(String passengerName) {
        List<TravelPackage> travelPackageList = travelPackageDao.findAll();
        List<TravelPackage> travelPackageListForUser = new ArrayList<>();
        if(travelPackageList == null)
            return travelPackageListForUser;
        for(TravelPackage travelPackage : travelPackageList) {
            Long[] passengerListIds = travelPackage.getPassengerListIds();
            for(Long passengerId : passengerListIds) {
                Passenger passenger = passengerDao.findOne(passengerId);
                if(passenger == null)
                    continue;
                if(passenger.getName().equalsIgnoreCase(passengerName))
                    travelPackageListForUser.add(travelPackage);
            }
        }
        return travelPackageListForUser;
    }

    @Override
    public List<TravelPackage> getAllTravelPackage() {

        return travelPackageDao.findAll();
    }
}
