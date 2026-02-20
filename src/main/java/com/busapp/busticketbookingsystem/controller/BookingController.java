package com.busapp.busticketbookingsystem.controller;

import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingRequestDTO;
import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingResponseDTO;
import com.busapp.busticketbookingsystem.entity.Booking;
import com.busapp.busticketbookingsystem.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponseDTO bookSeats(
            @RequestBody BookingRequestDTO request
    ) {
        String userEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return bookingService.createBooking(request, userEmail);
    }

    @GetMapping("/my")
    public List<BookingResponseDTO> myBookings() {
        String userEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return bookingService.getUserBooking(userEmail);
    }
}