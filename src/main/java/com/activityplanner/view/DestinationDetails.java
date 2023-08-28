package com.activityplanner.view;

import com.activityplanner.entity.Activity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;


public class DestinationDetails {


    @JsonProperty
    private String destinationName;

    @JsonProperty
    private List<Activity> activityList;

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }
}
