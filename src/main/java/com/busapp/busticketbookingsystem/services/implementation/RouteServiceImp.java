package com.busapp.busticketbookingsystem.services.implementation;

import com.busapp.busticketbookingsystem.dto.adminserviceDTO.RouteRequestDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.RouteResponseDto;
import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusResponseDTO;
import com.busapp.busticketbookingsystem.dto.busServiceDTO.SeatResponseDTO;
import com.busapp.busticketbookingsystem.entity.Route;
import com.busapp.busticketbookingsystem.reposistory.RouteRepository;
import com.busapp.busticketbookingsystem.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServiceImp implements RouteService {
    private final RouteRepository routeRepo;

    @Override
    @Transactional(readOnly = true)
    public List<BusResponseDTO> searchBuses(String source, String destination) {
        List<Route> routes = routeRepo.findBySourceIgnoreCaseAndDestinationIgnoreCase(
                source,
                destination
        );
        if(routes.isEmpty()){
            return Collections.emptyList();
        }

        List<BusResponseDTO> response = new ArrayList<>();
       return routes.stream().flatMap(
                route -> route.getBuses().stream()
        ).map(bus -> {
                   List<SeatResponseDTO> seatDtos =
                           bus.getSeats().stream()
                                   .map(seat -> new SeatResponseDTO(
                                           seat.getSeatId(),
                                           seat.getSeatNumber(),
                                           500.0,
                                           seat.getIsBooked()
                                   )).collect(Collectors.toList());
                   return new BusResponseDTO(
                           bus.getBusId(),
                           bus.getBusName(),
                           bus.getTotalSeat(),
                           seatDtos
                   );

        }).toList();
    }

    @Override
    public Route createRoute(RouteRequestDto request) {
        Route route = new Route();
        route.setSource(request.getSource());
        route.setDestination(request.getDestination());

        return routeRepo.save(route);

    }

    @Override
    public List<RouteResponseDto> getAllRoutes() {
        List<Route> routes = routeRepo.findAll();
        if(routes.isEmpty()) return Collections.emptyList();
        return routes.stream()
                .map(route -> new RouteResponseDto(
                        route.getRouteId(),
                        route.getSource(),
                        route.getDestination()
                )).toList();
    }

    @Override
    public RouteResponseDto getRoute(Long id) {
        Route route = routeRepo.findById(id).orElseThrow(() -> new RuntimeException("Route Not Found"));
        return new RouteResponseDto(
                route.getRouteId(),
                route.getSource(),
                route.getDestination()
        );
    }
}
