package com.activityplanner.dao;

import com.activityplanner.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceDao extends JpaRepository<Balance, Long> {

    List<Balance> findByPassengerId(Long passengerId);
}
