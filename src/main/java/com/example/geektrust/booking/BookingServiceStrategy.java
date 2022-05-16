package com.example.geektrust.booking;

import com.example.geektrust.dao.VehicleReservation;
import com.example.geektrust.dao.Vehicle;
import com.example.geektrust.model.Booking;

import java.util.List;

public interface BookingServiceStrategy {
     Vehicle bookVehicle(Booking booking, List<VehicleReservation> vehicleReservations, List<Vehicle> vehicleList);
}
