package com.busapp.busticketbookingsystem.reposistory;

import com.busapp.busticketbookingsystem.entity.BookingSeat;
import com.busapp.busticketbookingsystem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {
    boolean existsBySeatAndBooking_BookingDate(
            Seat seat, LocalDate bookingDate
    );
    public Boolean existsBySeat(Seat seat);
}
