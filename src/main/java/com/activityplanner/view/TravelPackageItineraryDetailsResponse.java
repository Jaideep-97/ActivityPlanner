package com.activityplanner.view;

import com.activityplanner.entity.Destination;

import java.util.List;

public class TravelPackageItineraryDetailsResponse {

    private String travelPackageName;

    private List<DestinationDetails> destinationList;

    public String getTravelPackageName() {
        return travelPackageName;
    }

    public void setTravelPackageName(String travelPackageName) {
        this.travelPackageName = travelPackageName;
    }

    public List<DestinationDetails> getDestinationList() {
        return destinationList;
    }

    public void setDestinationList(List<DestinationDetails> destinationList) {
        this.destinationList = destinationList;
    }
}
