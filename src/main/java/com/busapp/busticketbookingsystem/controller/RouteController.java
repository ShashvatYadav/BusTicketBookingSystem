package com.busapp.busticketbookingsystem.controller;


import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusResponseDTO;
import com.busapp.busticketbookingsystem.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping("/search")
    public ResponseEntity<List<BusResponseDTO>> searchBuses(
            @RequestParam String source,
            @RequestParam String destination){
        return ResponseEntity.ok(routeService.searchBuses(
                source, destination));
    }
}
