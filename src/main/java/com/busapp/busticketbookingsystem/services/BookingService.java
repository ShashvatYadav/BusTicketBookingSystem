package com.busapp.busticketbookingsystem.services;

import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingRequestDTO;
import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingResponseDTO;
import com.busapp.busticketbookingsystem.entity.Booking;

import java.util.List;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO request, String userEmail);
    List<BookingResponseDTO> getUserBooking(String userEmail);
}
