package com.rest.springapp.controller;

import com.rest.springapp.model.Owner;
import com.rest.springapp.service.OwnerService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService service;

    public OwnerController(OwnerService service) {
        this.service = service;
    }

    // Get all owners (Without Pagination)
    @GetMapping
    public List<Owner> getAllOwners() {
        return service.getAllOwners();
    }

    // Get owner by ID
    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        return service.getOwnerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new owner
    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) {
        return service.saveOwner(owner);
    }

    // Update owner by ID
    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        try {
            return ResponseEntity.ok(service.updateOwner(id, owner));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete owner by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        if (service.getOwnerById(id).isPresent()) {
            service.deleteOwner(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Pagination & Sorting
    @GetMapping("/paged")
    public Page<Owner> getOwnersPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return service.getOwnersPaged(page, size, sortBy, direction);
    }

    // Find owners by name
    @GetMapping("/search")
    public List<Owner> getOwnersByName(@RequestParam String name) {
        return service.getOwnersByName(name);
    }

    // Find owner by contact number
    @GetMapping("/contact/{contactNumber}")
    public ResponseEntity<Owner> getOwnerByContactNumber(@PathVariable String contactNumber) {
        Owner owner = service.getOwnerByContactNumber(contactNumber);
        return owner != null ? ResponseEntity.ok(owner) : ResponseEntity.notFound().build();
    }

    // Get total number of owners
    @GetMapping("/count")
    public Long getTotalOwnersCount() {
        return service.getTotalOwnersCount();
    }
}
