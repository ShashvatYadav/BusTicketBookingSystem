package com.busapp.busticketbookingsystem.reposistory;

import com.busapp.busticketbookingsystem.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
}
