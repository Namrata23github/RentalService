package com.example.geektrust.enums;

import com.example.geektrust.command.AddBranchCommand;
import com.example.geektrust.command.AddVehicleCommand;
import com.example.geektrust.command.BookCommand;
import com.example.geektrust.command.CommandInterface;
import com.example.geektrust.command.DisplayCommand;

import java.util.NoSuchElementException;

public enum Command {
    ADD_BRANCH ("ADD_BRANCH", new AddBranchCommand()),
    ADD_VEHICLE("ADD_VEHICLE", new AddVehicleCommand()),
    BOOK("BOOK", new BookCommand()),
    DISPLAY_VEHICLES("DISPLAY_VEHICLES", new DisplayCommand());

    CommandInterface commandInterface;
String commandName;
    Command(String commandName, CommandInterface command) {
        this.commandName = commandName;
        commandInterface =  command;
    }

    public CommandInterface getCommandInterface() {
        return commandInterface;
    }

    public static Command forValue(String value) {
        // You can cache the array returned by `values()` in the enum itself
        // Or build a map from `String` to `TestEnum` and use that here
        for (Command val: values()) {
            if (val.commandName.equals(value)) {
                return val;
            }
        }
        throw new NoSuchElementException();
    }
}
