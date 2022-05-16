package com.example.geektrust.command;

import com.example.geektrust.booking.BookingService;
import com.example.geektrust.booking.VehicleSelectionStrategyFactory;
import com.example.geektrust.enums.BookingType;
import com.example.geektrust.enums.VehicleType;
import com.example.geektrust.model.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookCommand implements CommandInterface {

BookingService bookingService = BookingService.getInstance();
    VehicleSelectionStrategyFactory vehicleSelectionStrategyFactory = VehicleSelectionStrategyFactory.getInstance();
    @Override
    public String execute(String[] variables) {
        assert variables.length == 5;
        String branchId = variables[1];
        VehicleType vehicleType =VehicleType.forValue(variables[2]);
        LocalDate now = LocalDate.now();
        LocalDateTime  startTime = LocalDateTime.of(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth(), Integer.valueOf(variables[3]).intValue(),0 );
        LocalDateTime  endTime = LocalDateTime.of(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth(), Integer.valueOf(variables[4]).intValue(),0 );

        Booking booking =  Booking.builder().startTime(startTime).endTime(endTime).branchName(branchId).vehicleType(vehicleType).build();
        int v = (int) bookingService.bookVehicle(vehicleSelectionStrategyFactory.getStrategy(BookingType.CHEAP_VEHICLE), booking);

        return String.valueOf(v);
    }
}
