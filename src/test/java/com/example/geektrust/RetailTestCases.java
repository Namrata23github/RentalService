package com.example.geektrust;

import com.example.geektrust.onboard.RentalService;
import org.junit.jupiter.api.Test;

public class RetailTestCases {

    RentalService rentalService = RentalService.getInstance();

    @Test
    public void onBoardBranchSuccess() {
       String[] a = "ADD_BRANCH B1 CAR,BIKE,VAN".split("\\s+");
        rentalService.rentCar(a).equals("TRUE");
    }

    @Test
    public void onBoardVehicleSuccess() {
        String[] a = "ADD_VEHICLE B1 CAR V1 500".split("\\s+");
        rentalService.rentCar(a).equals("TRUE");
    }

    @Test
    public void onBoardVehicleFailIFVehicleTypeIsNotPresent() {
        String[] a = "ADD_VEHICLE B1 BUS V5 2500".split("\\s+");
        rentalService.rentCar(a).equals("FALSE");
    }

    @Test
    public void testBookingFailIfVehicleTypeIsNotPresent() {
        String[] a = "BOOK B1 VAN 1 5".split("\\s+");
        rentalService.rentCar(a).equals("1");
    }


}
