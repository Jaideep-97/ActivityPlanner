package com.activityplanner.dao;

import com.activityplanner.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryDao extends JpaRepository<Itinerary, Long> {

    List<Itinerary> findByItineraryName(String itineraryName);
}
