package com.busapp.busticketbookingsystem.dto.adminserviceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteResponseDto {
    private Long id;
    private String source;
    private String destination;
}
