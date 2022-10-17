package com.formation.velo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.velo.model.Station;
import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer>{

    Optional<Station> findByRecordId(String recordID);
    
}

