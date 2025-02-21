package com.rest.springapp.repository;

import com.rest.springapp.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    // Find owners by name (Case insensitive search)
    @Query("SELECT o FROM Owner o WHERE LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Owner> findByName(@Param("name") String name);

    // Find owner by exact contact number
    @Query("SELECT o FROM Owner o WHERE o.contactNumber = :contactNumber")
    Owner findByContactNumber(@Param("contactNumber") String contactNumber);

    // Count total number of owners
    @Query("SELECT COUNT(o) FROM Owner o")
    Long countTotalOwners();
}
