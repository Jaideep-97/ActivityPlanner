package com.activityplanner.dao;

import com.activityplanner.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationDao extends JpaRepository<Destination, Long> {

    List<Destination> findByDestinationName(String destinationName);
}
