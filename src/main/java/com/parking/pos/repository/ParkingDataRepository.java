package com.parking.pos.repository;

import com.parking.pos.entity.ParkingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingDataRepository extends JpaRepository<ParkingData, Long> {
    Optional<ParkingData> findByVehiclePlateNumberAndCheckOutTimeIsNull(String plate);
}
