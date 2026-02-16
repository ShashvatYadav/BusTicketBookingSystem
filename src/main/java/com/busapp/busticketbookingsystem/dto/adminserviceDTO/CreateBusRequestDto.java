package com.busapp.busticketbookingsystem.dto.adminserviceDTO;

import lombok.Data;

@Data
public class CreateBusRequestDto {
    private String source;
    private String destination;
    private String busName;
    private Integer totalSeat;
}
