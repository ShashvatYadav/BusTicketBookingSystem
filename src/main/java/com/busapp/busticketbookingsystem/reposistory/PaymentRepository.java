package com.busapp.busticketbookingsystem.reposistory;

import com.busapp.busticketbookingsystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
