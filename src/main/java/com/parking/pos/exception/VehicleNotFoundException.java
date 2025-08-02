package com.parking.pos.exception;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String plate) {
        super("Kendaraan dengan plat " + plate + " tidak ditemukan.");
    }
}
