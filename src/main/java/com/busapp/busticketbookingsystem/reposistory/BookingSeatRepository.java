package com.busapp.busticketbookingsystem.reposistory;

import com.busapp.busticketbookingsystem.entity.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {
}
