package com.busapp.busticketbookingsystem.dto.userServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserBookingDTO {

    private Long bookingId;
    private LocalDate bookingDate;
    private String status;
    private Double totalPrice;
    private List<String> seatNumbers;
}
