package com.activityplanner.service;

import com.activityplanner.entity.Activity;

import java.util.List;

public interface DestinationService {

    List<Activity> getActivityListForDestination(String destinationName);

    void addActivityForDestination(String destinationName, String activityName);
}
