package com.busapp.busticketbookingsystem.controller;

import com.busapp.busticketbookingsystem.dto.adminserviceDTO.AdminBusResponseDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.CreateBusRequestDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.RouteRequestDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.RouteResponseDto;
import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusResponseDTO;
import com.busapp.busticketbookingsystem.entity.Bus;
import com.busapp.busticketbookingsystem.entity.Route;
import com.busapp.busticketbookingsystem.services.BusService;
import com.busapp.busticketbookingsystem.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add-bus")
    public ResponseEntity<Bus> createBus(@RequestBody CreateBusRequestDto requestDto){
        return ResponseEntity.ok(
                busService.createBuses(requestDto)
        );
    }
    @GetMapping("/buses")
    public ResponseEntity<List<AdminBusResponseDto>> getAllBuses(){
        return ResponseEntity.ok(busService.getAllBuses());
    }
    @GetMapping("/routes")
    public ResponseEntity<List<RouteResponseDto>> getAllRoutes(){
        return ResponseEntity.ok(routeService.getAllRoutes());
    }
    @GetMapping("/route/{id}")
    public ResponseEntity<RouteResponseDto> getRoute(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(routeService.getRoute(id));
    }

}
