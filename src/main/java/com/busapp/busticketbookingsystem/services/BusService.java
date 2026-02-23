package com.busapp.busticketbookingsystem.services;

import com.busapp.busticketbookingsystem.dto.adminserviceDTO.AdminBusResponseDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.CreateBusRequestDto;
import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusResponseDTO;
import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusWithSeatsDTO;
import com.busapp.busticketbookingsystem.entity.Bus;

import java.time.LocalDate;
import java.util.List;

public interface BusService {
     Bus createBuses(CreateBusRequestDto createBus);
     List<AdminBusResponseDto> getAllBuses();
    BusWithSeatsDTO getBusWithSeats(Long busId, LocalDate date);
}
