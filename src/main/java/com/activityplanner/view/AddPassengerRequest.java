package com.activityplanner.view;

import com.activityplanner.entity.PassengerType;

public class AddPassengerRequest {

    private String name;

    private String passengerType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }
}
