package com.rest.springapp.repository;

import com.rest.springapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.userId = :userId")
    List<Booking> findBookingsByUserId(@Param("userId") Long userId);

    @Query("SELECT b FROM Booking b WHERE b.boatHouseId = :boatHouseId")
    List<Booking> findBookingsByBoatHouseId(@Param("boatHouseId") Long boatHouseId);

    @Query("SELECT b FROM Booking b WHERE b.bookingDate BETWEEN :startDate AND :endDate")
    List<Booking> findBookingsByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(b.price) FROM Booking b WHERE b.boatHouseId = :boatHouseId")
    Double findTotalRevenueByBoatHouseId(@Param("boatHouseId") Long boatHouseId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.boatHouseId = :boatHouseId")
    Long countBookingsByBoatHouseId(@Param("boatHouseId") Long boatHouseId);
}
