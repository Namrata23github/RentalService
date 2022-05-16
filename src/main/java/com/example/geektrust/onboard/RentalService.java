package com.example.geektrust.onboard;

import com.example.geektrust.command.CommandInterface;
import com.example.geektrust.enums.Command;

public class RentalService {

    private static volatile RentalService instance;
    private static Object mutex = new Object();

    private RentalService() {
    }

    public static RentalService getInstance() {
        RentalService result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new RentalService();
            }
        }
        return result;
    }


    public String rentCar(String[] variables) {
            CommandInterface commandInterface = Command.forValue(variables[0]).getCommandInterface();
           return commandInterface.execute(variables);



    }
}
