package com.activityplanner.view;

public class AddItineraryRequest {

    private String itineraryName;

    private String[] destinationNames;

    public String getItineraryName() {
        return itineraryName;
    }

    public void setItineraryName(String itineraryName) {
        this.itineraryName = itineraryName;
    }

    public String[] getDestinationNames() {
        return destinationNames;
    }

    public void setDestinationNames(String[] destinationNames) {
        this.destinationNames = destinationNames;
    }
}
