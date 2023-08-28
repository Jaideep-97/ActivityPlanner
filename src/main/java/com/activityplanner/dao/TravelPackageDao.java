package com.activityplanner.dao;

import com.activityplanner.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPackageDao extends JpaRepository<TravelPackage, Long> {
}
