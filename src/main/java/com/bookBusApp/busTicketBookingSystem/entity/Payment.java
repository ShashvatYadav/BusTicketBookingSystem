package com.bookBusApp.busTicketBookingSystem.entity;

import com.bookBusApp.busTicketBookingSystem.enums.PaymentMode;
import com.bookBusApp.busTicketBookingSystem.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status paymentStatus;

    @OneToOne(mappedBy = "payment")
    private Booking booking;
}
