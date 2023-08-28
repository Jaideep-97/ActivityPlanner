package com.activityplanner.service;

import com.activityplanner.entity.Balance;

public interface BalanceService {

    Double getBalanceForUser(Long userId);

    void addBalance(Balance bal);
}
