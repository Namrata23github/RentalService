package com.example.geektrust.booking;


import com.example.geektrust.enums.VehicalStatus;
import com.example.geektrust.dao.VehicleReservation;
import com.example.geektrust.dao.Vehicle;
import com.example.geektrust.model.Booking;
import com.example.geektrust.utils.BookingUtils;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CheapestVehicleStrategy implements BookingServiceStrategy{


    private Double getTotalPrice(Booking booking, Vehicle cheapestVehicle) {
        Integer noOfHours = booking.getEndTime().getHour() - booking.getStartTime().getHour();
        Double price = cheapestVehicle.getPrice();
        return noOfHours * price;
    }

    private Optional<Vehicle> getCheapestVehicle(List<Vehicle> vehicles, List<String> availableBookedVehicleInGivenTimeSlot) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getVehicalStatus().equals(VehicalStatus.AVAILABLE)
                        || availableBookedVehicleInGivenTimeSlot.contains(vehicle.getId())).sorted(Comparator.comparing(Vehicle::getPrice)).findFirst();
    }


    @Override
    public Vehicle bookVehicle(Booking booking, List<VehicleReservation> vehicleReservations, List<Vehicle> vehicleList) {
        List<String> availableBookedVehicleInGivenTimeSlot = BookingUtils.getAvailableBookedVehicleInGivenTimeSlot(booking,vehicleReservations);
            Optional<Vehicle> cheapestVehicleOptional = getCheapestVehicle( vehicleList, availableBookedVehicleInGivenTimeSlot);
            if(cheapestVehicleOptional.isPresent()) {
                return cheapestVehicleOptional.get();
            } else {
                throw new NoSuchElementException();
            }

    }
}
