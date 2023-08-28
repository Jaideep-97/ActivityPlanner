package com.activityplanner.dao;

import com.activityplanner.entity.ActivityPassenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityPassengerDao extends JpaRepository<ActivityPassenger, Long> {

    List<ActivityPassenger> findByPassengerId(Long userId);

    List<ActivityPassenger> findByActivityId(Long activityId);

}
