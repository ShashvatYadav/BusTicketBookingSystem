package com.busapp.busticketbookingsystem.services;

import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingRequestDTO;
import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingResponseDTO;
import com.busapp.busticketbookingsystem.dto.userServiceDTO.UserBookingDTO;
import com.busapp.busticketbookingsystem.entity.Booking;

import java.util.List;

public interface BookingService {
    BookingResponseDTO createBooking(
            BookingRequestDTO request,
            String email
    );

    void cancelBooking(Long bookingId);
    public List<UserBookingDTO> getUserBooking(String userEmail);
}
