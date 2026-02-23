package com.busapp.busticketbookingsystem.dto.busServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BusWithSeatsDTO {
    private Long busId;
    private String busName;
    private List<SeatResponseDTO> seats;
}