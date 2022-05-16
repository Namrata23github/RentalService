package com.example.geektrust.dao;

import com.example.geektrust.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class VehicleReservation {
    UUID reservationId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    VehicleType vehicleType;
    String vehicleId;
    Address pickUpLocation;
    Address dropLocation;
    Double totalPrice;
    LocalDateTime reservationTime;

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof VehicleReservation)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        VehicleReservation b = (VehicleReservation) o;

        // Compare the data members and return accordingly
        return reservationId.equals(b.reservationId);
    }
}
