package com.busapp.busticketbookingsystem.dto.adminserviceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminBusResponseDto {

    private Long busId;
    private String busName;
    private Integer totalSeat;
    private String source;
    private String destination;
}