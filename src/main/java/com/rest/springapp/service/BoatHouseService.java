package com.rest.springapp.service;

import com.rest.springapp.model.BoatHouse;
import com.rest.springapp.repository.BoatHouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatHouseService {
    private final BoatHouseRepository repository;

    public BoatHouseService(BoatHouseRepository repository) {
        this.repository = repository;
    }

    // Get all boat houses
    public List<BoatHouse> getAllBoatHouses() {
        return repository.findAll();
    }

    // Get boat house by ID
    public Optional<BoatHouse> getBoatHouseById(Long id) {
        return repository.findById(id);
    }

    // Save a new boat house
    public BoatHouse saveBoatHouse(BoatHouse boatHouse) {
        return repository.save(boatHouse);
    }

    // Update an existing boat house
    public BoatHouse updateBoatHouse(Long id, BoatHouse boatHouse) {
        return repository.findById(id).map(existingBoatHouse -> {
            existingBoatHouse.setName(boatHouse.getName());
            existingBoatHouse.setLocation(boatHouse.getLocation());
            existingBoatHouse.setCapacity(boatHouse.getCapacity());
            return repository.save(existingBoatHouse);
        }).orElseThrow(() -> new RuntimeException("BoatHouse not found with id: " + id));
    }

    // Delete a boat house
    public void deleteBoatHouse(Long id) {
        repository.deleteById(id);
    }

    // Get paginated and sorted boat houses
    public Page<BoatHouse> getBoatHousesPaged(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    // Get boat houses by name
    public List<BoatHouse> getBoatHousesByName(String name) {
        return repository.findByNameContaining(name);
    }

    // Get boat houses by location
    public List<BoatHouse> getBoatHousesByLocation(String location) {
        return repository.findByLocation(location);
    }

    // Get boat houses sorted by capacity
    public List<BoatHouse> getBoatHousesSortedByCapacity() {
        return repository.findAllSortedByCapacity();
    }

    // Count the total number of boat houses
    public Long countBoatHouses() {
        return repository.countBoatHouses();
    }
}
