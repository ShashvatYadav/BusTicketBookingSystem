package com.busapp.busticketbookingsystem.dto.adminserviceDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Request to create new Route")
public class RouteRequestDto {
    @Schema(description = "Source name of the route", example = "Delhi", required = true)
    @NotBlank(message = "Source is required")
    private String source;
    @Schema(description = "Destination name of the route", example = "Agara", required = true)
    @NotBlank(message = "Destination is required")
    private String destination;
}
