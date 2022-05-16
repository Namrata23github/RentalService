package com.example.geektrust.booking;

import com.example.geektrust.enums.BookingType;

import java.util.HashMap;
import java.util.Map;

public class VehicleSelectionStrategyFactory {


    private static volatile VehicleSelectionStrategyFactory instance;
    private static Object mutex = new Object();


    public static VehicleSelectionStrategyFactory getInstance() {
        VehicleSelectionStrategyFactory result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new VehicleSelectionStrategyFactory();
            }
        }
        return result;
    }

    private Map<BookingType, BookingServiceStrategy> typeBookingServiceStrategyMap = new HashMap<>();

    private VehicleSelectionStrategyFactory() {
        typeBookingServiceStrategyMap.put(BookingType.CHEAP_VEHICLE, new CheapestVehicleStrategy());
    }

    public BookingServiceStrategy getStrategy(BookingType  bookingType) {
        return typeBookingServiceStrategyMap.get(BookingType.CHEAP_VEHICLE);
    }
}
