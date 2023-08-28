package com.activityplanner.service.impl;

import com.activityplanner.dao.ActivityDao;
import com.activityplanner.dao.ActivityPassengerDao;
import com.activityplanner.dao.DestinationDao;
import com.activityplanner.dao.PassengerDao;
import com.activityplanner.entity.Activity;
import com.activityplanner.entity.ActivityPassenger;
import com.activityplanner.entity.Destination;
import com.activityplanner.entity.Passenger;
import com.activityplanner.service.ActivityService;
import com.activityplanner.service.DestinationService;
import com.activityplanner.view.AddActivityRequest;
import com.activityplanner.view.AvailableActivitiesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private PassengerDao passengerDao;

    @Autowired
    private ActivityPassengerDao activityPassengerDao;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private DestinationDao destinationDao;


    @Override
    public void bookActivityForPassenger(String activityName, Long userId) {

        List<Activity> activityList = activityDao.findByActivityName(activityName);
        if(activityList == null || CollectionUtils.isEmpty(activityList))
            return;
        Activity activity = activityList.get(0);
        Passenger passenger = passengerDao.findOne(userId);
        if(activity == null || passenger == null)
            return;
        ActivityPassenger activityPassenger = new ActivityPassenger();
        activityPassenger.setPassengerId(passenger.getId());
        activityPassenger.setActivityId(activity.getId());
        activityPassengerDao.save(activityPassenger);

    }

    @Override
    public List<Activity> getActivitiesForPassenger(Long userId) {

        List<Activity> activityList = new ArrayList<>();
        List<ActivityPassenger> activityPassengerList = activityPassengerDao.findByPassengerId(userId);
        if(activityPassengerList != null && !CollectionUtils.isEmpty(activityPassengerList)) {
            for (ActivityPassenger activityPassenger : activityPassengerList) {
                Activity activity = activityDao.findOne(activityPassenger.getActivityId());
                if(activity != null)
                    activityList.add(activity);
            }
        }
        return activityList;

    }

    @Override
    public String getDestinationNameForActivity(String activityName) {

        List<Activity> activityList = activityDao.findByActivityName(activityName);
        return activityList == null || CollectionUtils.isEmpty(activityList)? null : activityList.get(0).getDestinationName();
    }

    @Override
    public Boolean isActivityFull(String activityName) {

        List<Activity> activityList = activityDao.findByActivityName(activityName);
        if(activityList == null || CollectionUtils.isEmpty(activityList))
            return Boolean.TRUE;
        Activity activity = activityList.get(0);
        List<ActivityPassenger> activityPassengerList = activityPassengerDao.findByActivityId(activity.getId());
        if(activityPassengerList == null || CollectionUtils.isEmpty(activityPassengerList))
            return Boolean.FALSE;
        if(activity.getCapacity() == activityPassengerList.size())
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    @Override
    public Activity getActivityByName(String activityName) {
        List<Activity> activityList =  activityDao.findByActivityName(activityName);
        if(activityList == null || CollectionUtils.isEmpty(activityList))
            return null;
        else
            return activityList.get(0);
    }

    @Override
    public void addActivity(AddActivityRequest addActivityRequest) {
        Activity activity = new Activity();
        activity.setActivityName(addActivityRequest.getActivityName());
        activity.setDestinationName(addActivityRequest.getDestinationName());
        activity.setCapacity(10);
        activity.setCost(100.00);
        activity.setDescription("Activity for destination "+addActivityRequest.getDestinationName());
        activityDao.save(activity);

        List<Activity> activityList = destinationService.getActivityListForDestination(addActivityRequest.getDestinationName());
        Long []oldActIds = new Long[activityList.size()+1];
        int k=0;
        for(Activity act : activityList) {
            oldActIds[k] = act.getId();
            k++;
        }
        oldActIds[activityList.size()] = activity.getId();
        List<Destination> destinationList = destinationDao.findByDestinationName(addActivityRequest.getDestinationName());
        if(destinationList == null || CollectionUtils.isEmpty(destinationList))
            return;
        Destination destination = destinationList.get(0);
        destination.setActivityListIds(oldActIds);
        destinationDao.save(destination);
    }

    @Override
    public Activity getActivityById(Long activityId) {
        return activityDao.getOne(activityId);
    }

}
