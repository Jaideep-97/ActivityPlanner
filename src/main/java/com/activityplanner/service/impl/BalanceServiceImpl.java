package com.activityplanner.service.impl;

import com.activityplanner.dao.BalanceDao;
import com.activityplanner.entity.Balance;
import com.activityplanner.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceDao balanceDao;


    @Override
    public Double getBalanceForUser(Long userId) {
        List<Balance> balanceList = balanceDao.findByPassengerId(userId);
        if(balanceList == null || CollectionUtils.isEmpty(balanceList))
            return null;
        return balanceList.get(0).getBalanceLeft();
    }

    @Override
    public void addBalance(Balance bal) {
        balanceDao.save(bal);
    }
}
