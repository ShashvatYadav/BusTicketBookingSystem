package com.busapp.busticketbookingsystem.controller;

import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusResponseDTO;
import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusWithSeatsDTO;
import com.busapp.busticketbookingsystem.services.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;

    @GetMapping("/{busId}")
    public BusWithSeatsDTO getBusDetails(
            @PathVariable Long busId,
            @RequestParam("date") LocalDate bookingDate
    ) {
        return busService.getBusWithSeats(busId, bookingDate);
    }
}
