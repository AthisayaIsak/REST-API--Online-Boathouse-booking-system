package com.rest.springapp.service;

import com.rest.springapp.model.Booking;
import com.rest.springapp.repository.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public List<Booking> getAllBookings() {
        return repository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return repository.findById(id);
    }

    public Booking saveBooking(Booking booking) {
        return repository.save(booking);
    }

    public Booking updateBooking(Long id, Booking updatedBooking) {
        return repository.findById(id).map(existingBooking -> {
            existingBooking.setUserId(updatedBooking.getUserId());
            existingBooking.setBoatHouseId(updatedBooking.getBoatHouseId());
            existingBooking.setBookingDate(updatedBooking.getBookingDate());
            existingBooking.setPrice(updatedBooking.getPrice());
            return repository.save(existingBooking);
        }).orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    public void deleteBooking(Long id) {
        repository.deleteById(id);
    }

    public Page<Booking> getBookingsPaged(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return repository.findBookingsByUserId(userId);
    }

    public List<Booking> getBookingsByBoatHouseId(Long boatHouseId) {
        return repository.findBookingsByBoatHouseId(boatHouseId);
    }

    public List<Booking> getBookingsByDateRange(LocalDate startDate, LocalDate endDate) {
        return repository.findBookingsByDateRange(startDate, endDate);
    }

    public Double getTotalRevenueByBoatHouseId(Long boatHouseId) {
        return repository.findTotalRevenueByBoatHouseId(boatHouseId);
    }

    public Long countBookingsByBoatHouseId(Long boatHouseId) {
        return repository.countBookingsByBoatHouseId(boatHouseId);
    }
}
