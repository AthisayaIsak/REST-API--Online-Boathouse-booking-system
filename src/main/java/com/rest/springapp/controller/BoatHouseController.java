package com.rest.springapp.controller;

import com.rest.springapp.model.BoatHouse;
import com.rest.springapp.service.BoatHouseService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boathouses")
public class BoatHouseController {
    private final BoatHouseService service;

    public BoatHouseController(BoatHouseService service) {
        this.service = service;
    }

    // Get all boat houses
    @GetMapping
    public List<BoatHouse> getAllBoatHouses() {
        return service.getAllBoatHouses();
    }

    // Get boat house by ID
    @GetMapping("/{id}")
    public Optional<BoatHouse> getBoatHouse(@PathVariable Long id) {
        return service.getBoatHouseById(id);
    }

    // Create a new boat house
    @PostMapping
    public BoatHouse createBoatHouse(@RequestBody BoatHouse boatHouse) {
        return service.saveBoatHouse(boatHouse);
    }

    // Update an existing boat house
    @PutMapping("/{id}")
    public BoatHouse updateBoatHouse(@PathVariable Long id, @RequestBody BoatHouse boatHouse) {
        return service.updateBoatHouse(id, boatHouse);
    }

    // Delete a boat house
    @DeleteMapping("/{id}")
    public void deleteBoatHouse(@PathVariable Long id) {
        service.deleteBoatHouse(id);
    }

    // Get boat houses by name
    @GetMapping("/by-name")
    public List<BoatHouse> getBoatHousesByName(@RequestParam String name) {
        return service.getBoatHousesByName(name);
    }

    // Get boat houses by location
    @GetMapping("/by-location")
    public List<BoatHouse> getBoatHousesByLocation(@RequestParam String location) {
        return service.getBoatHousesByLocation(location);
    }

    // Get boat houses sorted by capacity
    @GetMapping("/sorted-by-capacity")
    public List<BoatHouse> getBoatHousesSortedByCapacity() {
        return service.getBoatHousesSortedByCapacity();
    }

    // Get paginated and sorted boat houses
    @GetMapping("/paged")
    public Page<BoatHouse> getBoatHousesPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return service.getBoatHousesPaged(page, size, sortBy, direction);
    }

    // Get the total count of boat houses
    @GetMapping("/count")
    public Long countBoatHouses() {
        return service.countBoatHouses();
    }
}
