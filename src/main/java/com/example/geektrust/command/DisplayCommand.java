package com.example.geektrust.command;

import com.example.geektrust.booking.BookingService;
import com.example.geektrust.enums.VehicleType;
import com.example.geektrust.model.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DisplayCommand implements CommandInterface {
    BookingService bookingService = BookingService.getInstance();


    @Override
    public String execute(String[] variables) {
        assert variables.length == 3;
        String branchId = variables[1];
        LocalDate now = LocalDate.now();
        LocalDateTime startTime = LocalDateTime.of(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth(), Integer.valueOf(variables[2]).intValue(),0 );
        LocalDateTime  endTime = LocalDateTime.of(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth(), Integer.valueOf(variables[3]).intValue(),0 );

        Booking booking =  Booking.builder().startTime(startTime).endTime(endTime).branchName(branchId).build();
        return bookingService.displayAvailableVehicles(booking);
    }
}
