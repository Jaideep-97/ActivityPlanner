package com.activityplanner.service;

import com.activityplanner.dao.BalanceDao;
import com.activityplanner.entity.Balance;
import com.activityplanner.entity.PassengerType;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BalanceStrategy {

    @Autowired
    private BalanceDao balanceDao;


    public void updateBalanceForUser(Long userId, String activityName) {}
}
