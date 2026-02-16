package com.busapp.busticketbookingsystem.controller;

import com.busapp.busticketbookingsystem.dto.adminserviceDTO.CreateBusRequestDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.RouteRequestDto;
import com.busapp.busticketbookingsystem.entity.Bus;
import com.busapp.busticketbookingsystem.entity.Route;
import com.busapp.busticketbookingsystem.services.BusService;
import com.busapp.busticketbookingsystem.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final RouteService routeService;
    private final BusService busService;

    @GetMapping("/dashboard")
    public String dashboard(){
        return "Admin Dashboard";
    }

    @PostMapping("/route")
    public ResponseEntity<Route> createRoute(@RequestBody RouteRequestDto requestDto){
        return ResponseEntity.ok(routeService.createRoute(requestDto));
    }

    @PostMapping("/buses")
    public ResponseEntity<Bus> createBus(@RequestBody CreateBusRequestDto requestDto){
        return ResponseEntity.ok(
                busService.createBuses(requestDto)
        );
    }
}
