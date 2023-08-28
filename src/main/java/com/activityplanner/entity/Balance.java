package com.activityplanner.entity;

import javax.persistence.*;


@Entity
@Table(name="balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long passengerId;

    private Double balanceLeft;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public Double getBalanceLeft() {
        return balanceLeft;
    }

    public void setBalanceLeft(Double balanceLeft) {
        this.balanceLeft = balanceLeft;
    }
}
