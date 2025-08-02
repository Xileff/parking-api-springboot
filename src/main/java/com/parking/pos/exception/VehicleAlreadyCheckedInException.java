package com.parking.pos.exception;

public class VehicleAlreadyCheckedInException extends RuntimeException {
    public VehicleAlreadyCheckedInException(String plate) {
        super("Kendaraan dengan plat " + plate + " sudah check-in dan belum check-out.");
    }
}

