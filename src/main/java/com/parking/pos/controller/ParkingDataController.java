package com.parking.pos.controller;

import com.parking.pos.dto.request.CheckInRequest;
import com.parking.pos.dto.request.CheckOutRequest;
import com.parking.pos.dto.response.ParkingDataResponse;
import com.parking.pos.service.ParkingDataService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ParkingDataController {

    @Autowired
    private ParkingDataService service;

    @PostMapping("/check-in")
    public ResponseEntity<ParkingDataResponse> checkIn(@RequestBody CheckInRequest request) {
        ParkingDataResponse data = service.checkIn(request.getVehiclePlateNumber());
        return ResponseEntity.ok(data);
    }

    @GetMapping("/detail/{plate}")
    public ResponseEntity<ParkingDataResponse> getDetail(@PathVariable String plate) {
        ParkingDataResponse data = service.getDetail(plate);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/check-out")
    public ResponseEntity<ParkingDataResponse> checkOut(@RequestBody CheckOutRequest request) {
        ParkingDataResponse data = service.checkOut(request.getVehiclePlateNumber());
        return ResponseEntity.ok(data);
    }
}


