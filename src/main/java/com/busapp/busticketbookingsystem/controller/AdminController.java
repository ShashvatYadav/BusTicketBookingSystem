package com.busapp.busticketbookingsystem.controller;

import com.busapp.busticketbookingsystem.dto.adminserviceDTO.AdminBusResponseDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.CreateBusRequestDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.RouteRequestDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.RouteResponseDto;
import com.busapp.busticketbookingsystem.entity.Bus;
import com.busapp.busticketbookingsystem.entity.Route;
import com.busapp.busticketbookingsystem.services.BusService;
import com.busapp.busticketbookingsystem.services.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin API", description = "API for managing Routes and Buses")
@RequiredArgsConstructor
public class AdminController {

    private final RouteService routeService;
    private final BusService busService;

    @GetMapping("/dashboard")
    public String dashboard(){
        return "Admin Dashboard";
    }

    @Operation(summary = "Create a new Route",
    description = "Add a new route by given Source and Destination"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "New Route Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RouteRequestDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Input"
            )
    })
    @PostMapping("/route")
    public ResponseEntity<Route> createRoute(@RequestBody RouteRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(routeService.createRoute(requestDto));
    }

    @Operation(summary = "Create new Bus",
    description = "Create a new Bus by Source, Destination and Bus-Name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "New bus created"
            ),
            @ApiResponse(
                    responseCode = "400", description = "Invalid input"
            )
    })
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
