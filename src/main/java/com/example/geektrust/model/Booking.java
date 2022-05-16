package com.example.geektrust.model;

import com.example.geektrust.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Booking {
    String branchName;
    VehicleType vehicleType;
    LocalDateTime startTime;
    LocalDateTime endTime;
}
