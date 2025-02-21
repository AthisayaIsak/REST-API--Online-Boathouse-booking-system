package com.rest.springapp.repository;

import com.rest.springapp.model.BoatHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BoatHouseRepository extends JpaRepository<BoatHouse, Long>, PagingAndSortingRepository<BoatHouse, Long> {

    // Custom JPQL query to get boat houses by name
    @Query("SELECT b FROM BoatHouse b WHERE b.name LIKE %?1%")
    List<BoatHouse> findByNameContaining(String name);

    // Custom JPQL query to find boat houses by location
    @Query("SELECT b FROM BoatHouse b WHERE b.location = ?1")
    List<BoatHouse> findByLocation(String location);

    // Custom JPQL query for sorting boat houses by capacity
    @Query("SELECT b FROM BoatHouse b ORDER BY b.capacity DESC")
    List<BoatHouse> findAllSortedByCapacity();

    // JPQL query to count the number of boat houses
    @Query("SELECT COUNT(b) FROM BoatHouse b")
    Long countBoatHouses();
}
