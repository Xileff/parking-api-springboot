package com.parking.pos.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ParkingDataResponse {
    private String vehiclePlateNumber;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private Integer totalPrice;
}
