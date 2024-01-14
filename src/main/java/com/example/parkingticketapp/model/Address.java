package com.example.parkingticketapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private Integer number;
    @Column(name = "add_info")
    private String addInfo;
    @JsonIgnore
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Parking parking;
}
