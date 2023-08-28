package com.activityplanner.entity;

import javax.persistence.*;

@Entity
@Table(name="travel_agency_admin")
public class TravelAgencyAdmin extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
