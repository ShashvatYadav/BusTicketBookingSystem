package com.busapp.busticketbookingsystem.services.implementation;

import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingRequestDTO;
import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingResponseDTO;
import com.busapp.busticketbookingsystem.entity.*;
import com.busapp.busticketbookingsystem.enums.PaymentMode;
import com.busapp.busticketbookingsystem.enums.Status;
import com.busapp.busticketbookingsystem.reposistory.BookingRepository;
import com.busapp.busticketbookingsystem.reposistory.PaymentRepository;
import com.busapp.busticketbookingsystem.reposistory.SeatRepository;
import com.busapp.busticketbookingsystem.reposistory.UserRepository;
import com.busapp.busticketbookingsystem.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingServiceImp implements BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public BookingResponseDTO createBooking(
            BookingRequestDTO request,
            String userEmail
    ) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Seat> seats = seatRepository.findAllById(request.getSeatIds());

        if (seats.size() != request.getSeatIds().size()) {
            throw new RuntimeException("Some seats not found");
        }

        for (Seat seat : seats) {
            if (seat.getIsBooked()) {
                throw new RuntimeException(
                        "Seat already booked: " + seat.getSeatNumber()
                );
            }
            seat.setIsBooked(true);
        }

        // Create Payment first
        Payment payment = new Payment();
        payment.setPaymentMode(PaymentMode.UPI);
        payment.setPaymentStatus(Status.CONFIRM);
        payment = paymentRepository.save(payment);

        // Create Booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingDate(LocalDate.now());
        booking.setStatus(Status.CONFIRM);
        booking.setPayment(payment);

        double totalAmount = 0;

        for (Seat seat : seats) {

            double price = 500.0;
            totalAmount += price;

            BookingSeat bookingSeat = new BookingSeat();
            bookingSeat.setBooking(booking);
            bookingSeat.setSeat(seat);
            bookingSeat.setPrice(price);

            booking.getBookingSeats().add(bookingSeat);
        }

        booking = bookingRepository.save(booking);

        return new BookingResponseDTO(
                booking.getBookingId(),
                booking.getStatus().name(),
                totalAmount
        );
    }

    @Override
    public List<Booking> getUserBooking(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getBookings();
    }
}