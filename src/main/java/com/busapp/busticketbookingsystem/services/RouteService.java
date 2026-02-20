package com.busapp.busticketbookingsystem.services;

import com.busapp.busticketbookingsystem.dto.adminserviceDTO.RouteRequestDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.RouteResponseDto;
import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusResponseDTO;
import com.busapp.busticketbookingsystem.entity.Route;

import java.util.List;

public interface RouteService {
    List<BusResponseDTO> searchBuses(String source, String destination);
    Route createRoute(RouteRequestDto routeRequestDto);
    List<RouteResponseDto> getAllRoutes();
    RouteResponseDto getRoute(Long id);
}
