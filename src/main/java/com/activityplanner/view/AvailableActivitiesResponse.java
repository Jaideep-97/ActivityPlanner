package com.activityplanner.view;

import java.util.List;

public class AvailableActivitiesResponse {

    private String destinationName;

    private List<AvailableActivity> activitiesList;

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public List<AvailableActivity> getActivitiesList() {
        return activitiesList;
    }

    public void setActivitiesList(List<AvailableActivity> activitiesList) {
        this.activitiesList = activitiesList;
    }
}
