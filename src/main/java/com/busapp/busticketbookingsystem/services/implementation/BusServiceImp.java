package com.busapp.busticketbookingsystem.services.implementation;

import com.busapp.busticketbookingsystem.dto.adminserviceDTO.CreateBusRequestDto;
import com.busapp.busticketbookingsystem.entity.Bus;
import com.busapp.busticketbookingsystem.entity.Route;
import com.busapp.busticketbookingsystem.entity.Seat;
import com.busapp.busticketbookingsystem.reposistory.BusRepository;
import com.busapp.busticketbookingsystem.reposistory.RouteRepository;
import com.busapp.busticketbookingsystem.services.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusServiceImp implements BusService {

    private final BusRepository busRepo;
    private final RouteRepository routeRepo;


    @Override
    @Transactional
    public Bus createBuses(CreateBusRequestDto createBus) {
        Route route = routeRepo
                .findBySourceIgnoreCaseAndDestinationIgnoreCase(
                        createBus.getSource(),
                        createBus.getDestination()
                )
                .stream()
                .findFirst()
                .orElseGet(() ->{
                    Route newRoute = new Route();
                    newRoute.setSource(createBus.getSource());
                    newRoute.setDestination(createBus.getDestination());
                    return  routeRepo.save(newRoute);
                });

        Bus bus = new Bus();
        bus.setBusName(createBus.getBusName());
        bus.setTotalSeat(createBus.getTotalSeat());
        bus.setRoute(route);

        // generate Seats
        List<Seat> seatList = new ArrayList<>();
        for(int i=0; i<createBus.getTotalSeat(); i++){
            Seat seat = new Seat();
            seat.setSeatNumber("S" + i);
            seat.setIsBooked(false);
            seat.setBus(bus);

            seatList.add(seat);
        }

        bus.setSeats(seatList);

        return busRepo.save(bus);
    }
}
