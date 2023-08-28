package com.activityplanner.service.impl;

import com.activityplanner.dao.DestinationDao;
import com.activityplanner.dao.ItineraryDao;
import com.activityplanner.entity.Destination;
import com.activityplanner.entity.Itinerary;
import com.activityplanner.service.ItineraryService;
import com.activityplanner.view.AddItineraryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    @Autowired
    private ItineraryDao itineraryDao;

    @Autowired
    private DestinationDao destinationDao;


    @Override
    public List<Destination> getAllDestinationForItinerary(String itineraryName) {
        List<Itinerary> itineraryList = itineraryDao.findByItineraryName(itineraryName);
        List<Destination> destinationList = new ArrayList<>();
        if(itineraryList == null || CollectionUtils.isEmpty(itineraryList))
            return destinationList;
        Itinerary itinerary = itineraryList.get(0);
        for(Long destinationId : itinerary.getDestinationListIds()) {
            Destination destination = destinationDao.findOne(destinationId);
            destinationList.add(destination);
        }
        return destinationList;
    }

    @Override
    public void addDestinationForItinerary(String itineraryName, String destinationName) {

        List<Destination> destinations = destinationDao.findByDestinationName(destinationName);
        Destination destination = destinations.get(0);
        List<Itinerary> itineraryList = itineraryDao.findByItineraryName(itineraryName);
        if(itineraryList == null || CollectionUtils.isEmpty(itineraryList)) {
            Itinerary itinerary = new Itinerary();
            List<Long> destinationList = new ArrayList<>();
            destinationList.add(destination.getId());
            itinerary.setItineraryName(itineraryName);
            itinerary.setDestinationListIds(destinationList.toArray(new Long[0]));
            itineraryDao.save(itinerary);
        }
        else {
            Itinerary itinerary = itineraryList.get(0);
            List<Long> destinationList = Arrays.asList(itinerary.getDestinationListIds());
            destinationList.add(destination.getId());
            itinerary.setDestinationListIds(destinationList.toArray(new Long[0]));
            itineraryDao.save(itinerary);
        }
    }

    @Override
    public void addItinerary(AddItineraryRequest addItineraryRequest) {

        Itinerary itinerary = new Itinerary();
        itinerary.setItineraryName(addItineraryRequest.getItineraryName());
        Long []destIds = new Long[addItineraryRequest.getDestinationNames().length];
        int k=0;
        for(String destName: addItineraryRequest.getDestinationNames()) {
            List<Destination> destinationList = destinationDao.findByDestinationName(destName);
            if(destinationList == null || CollectionUtils.isEmpty(destinationList))
                continue;
            Destination destination = destinationList.get(0);
            destIds[k] = destination.getId();
            k++;
        }
        itinerary.setDestinationListIds(destIds);
        itineraryDao.save(itinerary);
    }
}
