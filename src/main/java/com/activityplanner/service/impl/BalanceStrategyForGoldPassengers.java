package com.activityplanner.service.impl;

import com.activityplanner.dao.BalanceDao;
import com.activityplanner.entity.Activity;
import com.activityplanner.entity.Balance;
import com.activityplanner.service.ActivityService;
import com.activityplanner.service.BalanceStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component(value = "BalanceStrategyForGoldPassengers")
public class BalanceStrategyForGoldPassengers extends BalanceStrategy {

    @Autowired
    private BalanceDao balanceDao;

    @Autowired
    private ActivityService activityService;


    public void updateBalanceForUser(Long userId, String activityName) {

        List<Balance> balanceList = balanceDao.findByPassengerId(userId);
        if(balanceList == null || CollectionUtils.isEmpty(balanceList))
            return;
        Balance balance = balanceList.get(0);
        Activity activity = activityService.getActivityByName(activityName);
        Double balanceLeft = balance.getBalanceLeft() - activity.getCost()*0.9;
        balance.setBalanceLeft(balanceLeft);
        balanceDao.save(balance);
    }
}
