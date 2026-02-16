package com.busapp.busticketbookingsystem.dto.busServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatResponseDTO {
    private Long seatId;
    private String seatNumber;
    private Double price;
    private Boolean isBooked;
}
