package com.busapp.busticketbookingsystem.controller;

import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusResponseDTO;
import com.busapp.busticketbookingsystem.services.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;

    @GetMapping("/{busId}")
    public BusResponseDTO getBusDetails(@PathVariable Long busId) {
        return busService.getBusWithSeats(busId);
    }
}
