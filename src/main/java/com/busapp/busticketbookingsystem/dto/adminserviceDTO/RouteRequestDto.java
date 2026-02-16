package com.busapp.busticketbookingsystem.dto.adminserviceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteRequestDto {
    private String source;
    private String destination;
}
