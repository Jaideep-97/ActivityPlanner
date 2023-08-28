package com.activityplanner.dao;

import com.activityplanner.entity.Activity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityDao extends JpaRepository<Activity, Long> {

    List<Activity> findByActivityName(String activityName);
}
