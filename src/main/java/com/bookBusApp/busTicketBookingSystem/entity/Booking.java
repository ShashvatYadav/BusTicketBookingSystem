package com.bookBusApp.busTicketBookingSystem.entity;


import com.bookBusApp.busTicketBookingSystem.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-generator")
    @SequenceGenerator(name = "seq-generator", allocationSize = 150)
    private Long booking_id;

    @Column(nullable = false)
    private LocalDate booking_date;

    @Enumerated(
            EnumType.STRING
    )
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
