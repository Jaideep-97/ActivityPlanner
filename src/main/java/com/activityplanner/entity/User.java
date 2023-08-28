package com.activityplanner.entity;

import javax.persistence.*;


@MappedSuperclass
public abstract class User {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
