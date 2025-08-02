package com.parking.pos.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CheckInRequest {
    @NotBlank(message = "Vehicle Plate Number must be filled!")
    private String vehiclePlateNumber;
}
