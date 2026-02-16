package com.busapp.busticketbookingsystem.dto.bookingserviceDTO;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequestDTO {
    private Long busId;
    private List<Long> seatIds;
}
