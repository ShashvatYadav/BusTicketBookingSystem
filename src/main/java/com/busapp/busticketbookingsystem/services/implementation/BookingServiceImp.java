package com.busapp.busticketbookingsystem.services.implementation;

import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingRequestDTO;
import com.busapp.busticketbookingsystem.dto.bookingserviceDTO.BookingResponseDTO;
import com.busapp.busticketbookingsystem.dto.userServiceDTO.UserBookingDTO;
import com.busapp.busticketbookingsystem.entity.*;
import com.busapp.busticketbookingsystem.enums.PaymentMode;
import com.busapp.busticketbookingsystem.enums.PaymentStatus;
import com.busapp.busticketbookingsystem.enums.Status;
import com.busapp.busticketbookingsystem.reposistory.*;
import com.busapp.busticketbookingsystem.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingServiceImp implements BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final BusRepository busRepository;
    private final double BASE_PRICE = 500;


    @Transactional
    @Override
    public BookingResponseDTO createBooking(
            BookingRequestDTO request,
            String email
    ) {

        Bus bus = busRepository.findById(request.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        for (Long seatId : request.getSeatIds()) {
            boolean exists =
                    bookingSeatRepository
                            .existsBySeatSeatIdAndBookingBookingDate(
                                    seatId,
                                    request.getBookingDate()
                            );

            if (exists) {
                throw new RuntimeException("Seat already booked");
            }
        }

        Payment payment = new Payment();
        payment.setAmount(request.getSeatIds().size() * BASE_PRICE);
        payment.setPaymentMode(request.getPaymentMode());
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setTransactionId("TXN" + System.currentTimeMillis());
        payment.setPaymentTime(LocalDateTime.now());

        Booking booking = new Booking();
        booking.setBus(bus);
        booking.setUser(user);
        booking.setBookingDate(request.getBookingDate());
        booking.setStatus(Status.CONFIRMED);
        booking.setPayment(payment);

        List<BookingSeat> bookingSeats = new ArrayList<>();

        for (Long seatId : request.getSeatIds()) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new RuntimeException("Seat not found"));

            BookingSeat bs = new BookingSeat();
            bs.setBooking(booking);
            bs.setSeat(seat);
            bs.setPrice(500.0);
            bs.setBookingDate(request.getBookingDate());
            bookingSeats.add(bs);
        }

        booking.setBookingSeats(bookingSeats);
        Booking saved = bookingRepository.save(booking);
        List<String> seatNumbers =
                saved.getBookingSeats().stream()
                        .map(bs -> bs.getSeat().getSeatNumber())
                        .toList();

        return new BookingResponseDTO(
                saved.getBookingId(),
                saved.getBookingDate(),
                seatNumbers,
                saved.getStatus().name()
        );
    }

    @Transactional
    @Override
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(Status.CANCELLED);
    }


    @Transactional(readOnly = true)
    @Override
    public List<UserBookingDTO> getUserBooking(String userEmail) {

        List<Booking> bookings =
                bookingRepository.findBookingsWithSeatsByUserEmail(userEmail);

        return bookings.stream()
                .map(b -> new UserBookingDTO(
                        b.getBookingId(),
                        b.getBookingDate(),
                        b.getStatus().name(),
                        b.getBookingSeats()
                                .stream()
                                .mapToDouble(BookingSeat::getPrice)
                                .sum(),
                        b.getBookingSeats()
                                .stream()
                                .map(bs -> bs.getSeat().getSeatNumber())
                                .toList()
                ))
                .toList();
    }
}