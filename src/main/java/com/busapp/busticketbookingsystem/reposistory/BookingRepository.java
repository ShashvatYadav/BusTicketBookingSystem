package com.busapp.busticketbookingsystem.reposistory;

import com.busapp.busticketbookingsystem.entity.Booking;
import com.busapp.busticketbookingsystem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBusBusIdAndBookingDate(Long busId, LocalDate bookingDate);
    @Query("""   
    SELECT b FROM Booking b
    JOIN FETCH b.bookingSeats bs
    JOIN FETCH bs.seat
    WHERE b.user.email = :email
""")
    List<Booking> findBookingsWithSeatsByUserEmail(String email);
}
