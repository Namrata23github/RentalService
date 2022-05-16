package com.example.geektrust.enums;

import java.util.NoSuchElementException;

public enum VehicleType {
    CAR ("CAR"),
    BUS ("BUS"),
    VAN ("VAN"),
    BIKE ("BIKE");

    String vehicleType;
    VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public static VehicleType forValue(String value) {
        // You can cache the array returned by `values()` in the enum itself
        // Or build a map from `String` to `TestEnum` and use that here
        for (VehicleType val: values()) {
            if (val.vehicleType.equals(value)) {
                return val;
            }
        }
        throw new NoSuchElementException();
    }
}
