package com.activityplanner.dao;

import com.activityplanner.entity.TravelAgencyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelAgencyAdminDao extends JpaRepository<TravelAgencyAdmin, Long> {
}
