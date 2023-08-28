package com.activityplanner.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinationName;

    private Long[] activityListIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Long[] getActivityListIds() {
        return activityListIds;
    }

    public void setActivityListIds(Long[] activityListIds) {
        this.activityListIds = activityListIds;
    }
}
