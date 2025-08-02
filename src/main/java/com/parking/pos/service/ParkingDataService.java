package com.parking.pos.service;

import com.parking.pos.entity.ParkingData;
import com.parking.pos.dto.response.ParkingDataResponse;
import com.parking.pos.exception.VehicleAlreadyCheckedInException;
import com.parking.pos.exception.VehicleNotFoundException;
import com.parking.pos.repository.ParkingDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParkingDataService {

    @Autowired
    private ParkingDataRepository repository;

    private final int HOURLY_RATE = 3000;

    public ParkingDataResponse checkIn(String plate) {
        Optional<ParkingData> active = repository.findByVehiclePlateNumberAndCheckOutTimeIsNull(plate);
        if (active.isPresent()) {
            throw new VehicleAlreadyCheckedInException(plate);
        }

        ParkingData data = new ParkingData();
        data.setVehiclePlateNumber(plate);
        data.setCheckInTime(LocalDateTime.now());
        repository.save(data);

        return convertToResponse(data);
    }

    public ParkingDataResponse getDetail(String plate) {
        Optional<ParkingData> parkingData = repository.findByVehiclePlateNumberAndCheckOutTimeIsNull(plate);
        if (parkingData.isEmpty()) {
            throw new VehicleNotFoundException(plate);
        }

        return convertToResponse(parkingData.get());
    }
    public ParkingDataResponse checkOut(String plate) {
        ParkingData activeParkingData = repository.findByVehiclePlateNumberAndCheckOutTimeIsNull(plate)
                .orElseThrow(() -> new VehicleNotFoundException(plate));

        LocalDateTime now = LocalDateTime.now();
        long durationInHours = ChronoUnit.HOURS.between(activeParkingData.getCheckInTime(), now);
        durationInHours = durationInHours == 0 ? 1 : durationInHours;

        activeParkingData.setCheckOutTime(now);
        activeParkingData.setTotalPrice((int) durationInHours * HOURLY_RATE);
        repository.save(activeParkingData);

        return convertToResponse(activeParkingData);
    }

    private ParkingDataResponse convertToResponse(ParkingData parkingData) {
        ParkingDataResponse parkingDataResponse = new ParkingDataResponse();
        parkingDataResponse.setVehiclePlateNumber(parkingData.getVehiclePlateNumber());
        parkingDataResponse.setCheckInTime(parkingData.getCheckInTime());
        parkingDataResponse.setCheckOutTime(parkingData.getCheckOutTime());
        parkingDataResponse.setTotalPrice(parkingData.getTotalPrice());

        return parkingDataResponse;
    }
}

