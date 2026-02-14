package com.bookBusApp.busTicketBookingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bus_seq")
    @SequenceGenerator(
            name = "bus_seq",
            sequenceName = "bus_sequence",
            allocationSize = 1,
            initialValue = 1000
    )
    private  Long busId;

    @Column(nullable = false)
    private String busName;

    @Column(nullable = false)
    private Integer totalSeat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @OneToMany(
            mappedBy = "bus",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Seat> seats = new ArrayList<>();

}
