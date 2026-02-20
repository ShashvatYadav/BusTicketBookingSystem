package com.busapp.busticketbookingsystem.services.implementation;

import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingRequestDTO;
import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingResponseDTO;
import com.busapp.busticketbookingsystem.entity.*;
import com.busapp.busticketbookingsystem.enums.PaymentMode;
import com.busapp.busticketbookingsystem.enums.Status;
import com.busapp.busticketbookingsystem.reposistory.*;
import com.busapp.busticketbookingsystem.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingServiceImp implements BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final double BASE_PRICE = 500;
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
        double totalAmount = 0;

        for (Seat seat : seats) {

            if (!seat.getBus().getBusId()
                    .equals(request.getBusId())) {
                throw new RuntimeException("Invalid seat for this bus");
            }

            boolean alreadyBooked =
                    bookingSeatRepository
                            .existsBySeatAndBooking_BookingDate(
                                    seat,
                                    request.getBookingDate()
                            );

            if (alreadyBooked) {
                throw new RuntimeException(
                        "Seat already booked: " + seat.getSeatNumber()
                );
            }

            totalAmount += BASE_PRICE;
        }
        Payment payment = new Payment();
        payment.setPaymentMode(PaymentMode.UPI);
        payment.setPaymentStatus(Status.CONFIRM);
        payment = paymentRepository.save(payment);
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingDate(request.getBookingDate());
        booking.setStatus(Status.CONFIRM);
        booking.setPayment(payment);

        for (Seat seat : seats) {

            BookingSeat bookingSeat = new BookingSeat();
            bookingSeat.setBooking(booking);
            bookingSeat.setSeat(seat);
            bookingSeat.setPrice(BASE_PRICE);

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
    public List<BookingResponseDTO> getUserBooking(String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getBookings()
                .stream()
                .map(b -> new BookingResponseDTO(
                        b.getBookingId(),
                        b.getStatus().name(),
                        b.getBookingSeats()
                                .stream()
                                .mapToDouble(BookingSeat::getPrice)
                                .sum()
                ))
                .toList();
    }
}