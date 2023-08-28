package com.activityplanner.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="itinerary")
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itineraryName;


    private Long[] destinationListIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItineraryName() {
        return itineraryName;
    }


    public void setItineraryName(String itineraryName) {
        this.itineraryName = itineraryName;
    }

    public Long[] getDestinationListIds() {
        return destinationListIds;
    }

    public void setDestinationListIds(Long[] destinationListIds) {
        this.destinationListIds = destinationListIds;
    }
}
