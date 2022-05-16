package com.example.geektrust;

import com.example.geektrust.onboard.RentalService;
import org.junit.Before;
import org.junit.Test;

public class BookingTest {
    RentalService rentalService = RentalService.getInstance();

    @Before
    public void setup() {
        String[] a = "ADD_BRANCH B1 CAR,BIKE,VAN".split("\\s+");
        rentalService.rentCar(a);
        String[] b = "ADD_VEHICLE B1 CAR V1 500".split("\\s+");
        rentalService.rentCar(b);

         b = "ADD_VEHICLE B1 CAR V2 1000".split("\\s+");
        rentalService.rentCar(b);
        b = "ADD_VEHICLE B1 BIKE V3 250".split("\\s+");
        rentalService.rentCar(b);
        b = "ADD_VEHICLE B1 BIKE V4 300".split("\\s+");
        rentalService.rentCar(b);
        b = "ADD_VEHICLE B1 BUS V5 2500".split("\\s+");
        rentalService.rentCar(b);
    }
    @Test
    public void testBookingSuccessAndGetPrice() {
        String[] a = "BOOK B1 CAR 1 3".split("\\s+");
        rentalService.rentCar(a).equals("1000");
    }

    @org.junit.Test
    public void testBookingSuccessAndGetPriceIncreasedBy10PercentWhenDemandIsHigh() {
        String[] a = "BOOK B1 CAR 1 3".split("\\s+");
        rentalService.rentCar(a).equals("1000");

        String[] b = "BOOK B1 BIKE 2 3".split("\\s+");
        rentalService.rentCar(b).equals("250");


        String[] d = "BOOK B1 BIKE 5 6".split("\\s+");
        rentalService.rentCar(d).equals("500");

    }
}
