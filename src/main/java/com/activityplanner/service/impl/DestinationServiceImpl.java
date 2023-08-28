package com.activityplanner.service.impl;

import com.activityplanner.dao.ActivityDao;
import com.activityplanner.dao.DestinationDao;
import com.activityplanner.entity.Activity;
import com.activityplanner.entity.Destination;
import com.activityplanner.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationDao destinationDao;

    @Autowired
    private ActivityDao activityDao;

    @Override
    public List<Activity> getActivityListForDestination(String destinationName) {
        List<Destination> destinationList = destinationDao.findByDestinationName(destinationName);
        List<Activity> activityList = new ArrayList<>();
        if(destinationList == null || CollectionUtils.isEmpty(destinationList))
            return activityList;
        Long[] activityListIds = destinationList.get(0).getActivityListIds();
        if(activityListIds == null)
            return activityList;
        for (Long activityListId : activityListIds) {
            Activity activity = activityDao.getOne(activityListId);
            if(activity != null)
                activityList.add(activity);
        }
        return CollectionUtils.isEmpty(activityList) ? null : activityList;
    }

    @Override
    public void addActivityForDestination(String destinationName, String activityName) {
        List<Destination> destinationList = destinationDao.findByDestinationName(destinationName);
        if(destinationList == null || CollectionUtils.isEmpty(destinationList))
            return;
        Destination destination = destinationList.get(0);
        Long []activityListIds = destination.getActivityListIds();
        Activity activity = new Activity();
        activity.setActivityName(activityName);
        activity.setDestinationName(destinationName);
        activityDao.save(activity);
        List<Long> activityIdsList = Arrays.asList(activityListIds);
        activityIdsList.add(activity.getId());
        destination.setActivityListIds(activityIdsList.toArray(new Long[0]));
        destinationDao.save(destination);
    }
}
