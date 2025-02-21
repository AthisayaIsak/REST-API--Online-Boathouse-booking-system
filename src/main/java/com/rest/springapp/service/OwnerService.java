package com.rest.springapp.service;

import com.rest.springapp.model.Owner;
import com.rest.springapp.repository.OwnerRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository repository;

    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    // Get all owners (Without Pagination)
    public List<Owner> getAllOwners() {
        return repository.findAll();
    }

    // Get owner by ID
    public Optional<Owner> getOwnerById(Long id) {
        return repository.findById(id);
    }

    // Create a new owner
    public Owner saveOwner(Owner owner) {
        return repository.save(owner);
    }

    // Update an existing owner
    public Owner updateOwner(Long id, Owner owner) {
        return repository.findById(id).map(existingOwner -> {
            existingOwner.setName(owner.getName());
            existingOwner.setContactNumber(owner.getContactNumber());
            return repository.save(existingOwner);
        }).orElseThrow(() -> new RuntimeException("Owner not found with ID: " + id));
    }

    // Delete owner by ID
    public void deleteOwner(Long id) {
        repository.deleteById(id);
    }

    // Pagination & Sorting
    public Page<Owner> getOwnersPaged(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    // Find owners by name (Case insensitive)
    public List<Owner> getOwnersByName(String name) {
        return repository.findByName(name);
    }

    // Find owner by contact number
    public Owner getOwnerByContactNumber(String contactNumber) {
        return repository.findByContactNumber(contactNumber);
    }

    // Get total owner count
    public Long getTotalOwnersCount() {
        return repository.countTotalOwners();
    }
}
