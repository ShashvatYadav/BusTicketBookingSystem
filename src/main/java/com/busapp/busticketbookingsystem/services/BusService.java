package com.busapp.busticketbookingsystem.services;

import com.busapp.busticketbookingsystem.dto.adminserviceDTO.CreateBusRequestDto;
import com.busapp.busticketbookingsystem.entity.Bus;

public interface BusService {
    public Bus createBuses(CreateBusRequestDto createBus);
}
