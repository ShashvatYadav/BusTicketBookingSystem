package com.busapp.busticketbookingsystem.dto.busServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BusResponseDTO {
    private Long busId;
    private String busName;
    private Integer totalSeat;
    private List<SeatResponseDTO> seats;
}
