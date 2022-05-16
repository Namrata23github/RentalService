package com.example.geektrust.utils;

import com.example.geektrust.dao.VehicleReservation;
import com.example.geektrust.enums.VehicalStatus;
import com.example.geektrust.dao.Vehicle;
import com.example.geektrust.model.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BookingUtils {
    public static boolean isOverlap(LocalDateTime bookedStartTime, LocalDateTime bookedEndTime, LocalDateTime desireStartTime, LocalDateTime desireEndTime) {
        return !bookedStartTime.isAfter(desireEndTime) && !desireStartTime.isAfter(bookedEndTime);
    }

    public static boolean isDemand( List<Vehicle> vehicles) {
        return MathUtils.calculatePercentage(vehicles.stream().filter(vehicle -> vehicle.getVehicalStatus().equals(VehicalStatus.BOOKED)).count(), vehicles.stream().count()) >= 80 ;
    }

    public static List<String> getAvailableBookedVehicleInGivenTimeSlot(Booking booking, List<VehicleReservation> vehicalReservations) {
        return vehicalReservations.stream()
                .filter(v -> v.getVehicleType().equals(booking.getVehicleType()) &&
                        !BookingUtils.isOverlap(v.getStartTime(), v.getEndTime(), booking.getStartTime(), booking.getEndTime()))
                .map(VehicleReservation::getVehicleId)
                .collect(Collectors.toList());
    }

}
