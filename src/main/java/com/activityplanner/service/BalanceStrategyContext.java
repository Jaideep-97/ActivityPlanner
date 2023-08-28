package com.activityplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanceStrategyContext {

    @Autowired
    private BalanceStrategy balanceStrategy;

    public BalanceStrategyContext(BalanceStrategy balanceStrategy) {
        this.balanceStrategy = balanceStrategy;
    }

    public void doOperation(Long userId, String activityName) {
        balanceStrategy.updateBalanceForUser(userId, activityName);
    }
}
