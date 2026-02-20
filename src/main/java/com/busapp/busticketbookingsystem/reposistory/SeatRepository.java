package com.busapp.busticketbookingsystem.reposistory;

import com.busapp.busticketbookingsystem.entity.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Seat s where s.seatId in :ids")
    List<Seat> findSeatsForUpdate(@Param("ids") List<Long> ids);
}
