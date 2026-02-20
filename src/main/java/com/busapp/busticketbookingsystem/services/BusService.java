package com.busapp.busticketbookingsystem.services;

import com.busapp.busticketbookingsystem.dto.adminserviceDTO.AdminBusResponseDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.CreateBusRequestDto;
import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusResponseDTO;
import com.busapp.busticketbookingsystem.entity.Bus;

import java.util.List;

public interface BusService {
    public Bus createBuses(CreateBusRequestDto createBus);
    public List<AdminBusResponseDto> getAllBuses();
    public BusResponseDTO getBusWithSeats(Long busId);
}
