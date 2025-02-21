package com.rest.springapp.controller;

import com.rest.springapp.model.Booking;
import com.rest.springapp.service.BookingService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return service.getAllBookings();
    }

    @GetMapping("/{id}")
    public Optional<Booking> getBookingById(@PathVariable Long id) {
        return service.getBookingById(id);
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return service.saveBooking(booking);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        return service.updateBooking(id, updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        service.deleteBooking(id);
    }

    @GetMapping("/paged")
    public Page<Booking> getBookingsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "bookingDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return service.getBookingsPaged(page, size, sortBy, direction);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        return service.getBookingsByUserId(userId);
    }

    @GetMapping("/boathouse/{boatHouseId}")
    public List<Booking> getBookingsByBoatHouseId(@PathVariable Long boatHouseId) {
        return service.getBookingsByBoatHouseId(boatHouseId);
    }

    @GetMapping("/date-range")
    public List<Booking> getBookingsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return service.getBookingsByDateRange(startDate, endDate);
    }

    @GetMapping("/revenue/{boatHouseId}")
    public Double getTotalRevenueByBoatHouseId(@PathVariable Long boatHouseId) {
        return service.getTotalRevenueByBoatHouseId(boatHouseId);
    }

    @GetMapping("/count/{boatHouseId}")
    public Long countBookingsByBoatHouseId(@PathVariable Long boatHouseId) {
        return service.countBookingsByBoatHouseId(boatHouseId);
    }
}
