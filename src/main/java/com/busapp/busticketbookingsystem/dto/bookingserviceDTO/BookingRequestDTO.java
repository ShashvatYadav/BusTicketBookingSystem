package com.busapp.busticketbookingsystem.dto.bookingserviceDTO;

import com.busapp.busticketbookingsystem.enums.PaymentMode;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookingRequestDTO {
    private Long busId;
    private List<Long> seatIds;
    private LocalDate bookingDate;
    private PaymentMode paymentMode;
}
