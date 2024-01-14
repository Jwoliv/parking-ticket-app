package com.example.parkingticketapp.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parking")
public class Parking {
    @Id
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "total_parking_spaces")
    private Long totalParkingSpaces;
    @Column(name = "available_parking_spaces")
    private Long availableParkingSpaces;
    @Column(name = "price_per_hour")
    private Float pricePerHour;
}
