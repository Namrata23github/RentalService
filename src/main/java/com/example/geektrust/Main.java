package com.example.geektrust;

import com.example.geektrust.onboard.RentalService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static RentalService rentalService = RentalService.getInstance();

    public static void main(String[] args) {

        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands

                String s = sc.nextLine();
                System.out.println(rentalService.rentCar(s.split("\\s+")));
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }

    }
}
