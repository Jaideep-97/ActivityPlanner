package com.activityplanner.service;


import com.activityplanner.entity.Activity;
import com.activityplanner.view.AddActivityRequest;
import com.activityplanner.view.AvailableActivitiesResponse;

import java.util.List;

public interface ActivityService {

    void bookActivityForPassenger(String activityName, Long userId);

    List<Activity> getActivitiesForPassenger(Long userId);

    String getDestinationNameForActivity(String activityName);

    Boolean isActivityFull(String activityName);

    Activity getActivityByName(String activityName);

    void addActivity(AddActivityRequest addActivityRequest);

    Activity getActivityById(Long activityId);


}
