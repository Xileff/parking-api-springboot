package com.parking.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "parking_data")
public class ParkingData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehiclePlateNumber;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private Integer totalPrice;
}
