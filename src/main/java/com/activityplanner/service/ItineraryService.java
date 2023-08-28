package com.activityplanner.service;

import com.activityplanner.entity.Destination;
import com.activityplanner.view.AddItineraryRequest;

import java.util.List;

public interface ItineraryService {

    List<Destination> getAllDestinationForItinerary(String itineraryName);

    void addDestinationForItinerary(String itineraryName, String destinationName);

    void addItinerary(AddItineraryRequest addItineraryRequest);
}
