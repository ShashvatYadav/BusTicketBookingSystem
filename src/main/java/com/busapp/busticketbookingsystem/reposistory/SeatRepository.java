package com.busapp.busticketbookingsystem.reposistory;

import com.busapp.busticketbookingsystem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByBusBusId(Long busId);

}
