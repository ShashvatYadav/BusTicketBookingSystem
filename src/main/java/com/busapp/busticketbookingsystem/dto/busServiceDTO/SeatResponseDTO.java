package com.busapp.busticketbookingsystem.dto.busServiceDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponseDTO {
    private Long seatId;
    private String seatNumber;
    private boolean isBooked;
}