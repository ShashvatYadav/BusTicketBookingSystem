package com.busapp.busticketbookingsystem.dto.bookingserviceDTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
    private Long bookingId;
    private LocalDate bookingDate;
    private List<String> seatNumbers;
    private String status;
}
