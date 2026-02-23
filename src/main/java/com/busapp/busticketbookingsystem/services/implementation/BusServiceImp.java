package com.busapp.busticketbookingsystem.services.implementation;

import com.busapp.busticketbookingsystem.dto.adminserviceDTO.AdminBusResponseDto;
import com.busapp.busticketbookingsystem.dto.adminserviceDTO.CreateBusRequestDto;
import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusResponseDTO;
import com.busapp.busticketbookingsystem.dto.busServiceDTO.BusWithSeatsDTO;
import com.busapp.busticketbookingsystem.dto.busServiceDTO.SeatResponseDTO;
import com.busapp.busticketbookingsystem.entity.Booking;
import com.busapp.busticketbookingsystem.entity.Bus;
import com.busapp.busticketbookingsystem.entity.Route;
import com.busapp.busticketbookingsystem.entity.Seat;
import com.busapp.busticketbookingsystem.reposistory.BookingRepository;
import com.busapp.busticketbookingsystem.reposistory.BookingSeatRepository;
import com.busapp.busticketbookingsystem.reposistory.BusRepository;
import com.busapp.busticketbookingsystem.reposistory.RouteRepository;
import com.busapp.busticketbookingsystem.services.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusServiceImp implements BusService {

    private final BusRepository busRepo;
    private final RouteRepository routeRepo;
    private final BookingSeatRepository bookingSeatRepo;
    private final BookingRepository bookingRepo;


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
        for(int i=1; i<=createBus.getTotalSeat(); i++){
            Seat seat = new Seat();
            seat.setSeatNumber("S" + i);
            seat.setBus(bus);
            seatList.add(seat);
        }

        bus.setSeats(seatList);

        return busRepo.save(bus);
    }

    public List<AdminBusResponseDto> getAllBuses(){
        List<Bus> buses = busRepo.findAll();
        if(buses.size()>0){
                return buses.stream()
                        .map(bus -> new AdminBusResponseDto(
                                bus.getBusId(),
                                bus.getBusName(),
                                bus.getTotalSeat(),
                                bus.getRoute().getSource(),
                                bus.getRoute().getDestination()
                        )).toList();

        } else return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public BusWithSeatsDTO getBusWithSeats(Long busId, LocalDate date) {

        Bus bus = busRepo.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        Set<Long> bookedSeatIds =
                bookingRepo.findByBusBusIdAndBookingDate(busId, date)
                        .stream()
                        .flatMap(b -> b.getBookingSeats().stream())
                        .map(bs -> bs.getSeat().getSeatId())
                        .collect(Collectors.toSet());
        var seatDTOs = bus.getSeats()
                .stream()
                .map(seat -> new SeatResponseDTO(
                        seat.getSeatId(),
                        seat.getSeatNumber(),
                        bookedSeatIds.contains(seat.getSeatId())
                ))
                .toList();

        return new BusWithSeatsDTO(
                bus.getBusId(),
                bus.getBusName(),
                seatDTOs
        );
    }

    private Boolean isSeatBooked(Seat seat) {
        return bookingSeatRepo.existsBySeat(seat);
    }
}
