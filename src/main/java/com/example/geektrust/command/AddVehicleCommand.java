package com.example.geektrust.command;

import com.example.geektrust.dao.Vehicle;
import com.example.geektrust.enums.VehicalStatus;
import com.example.geektrust.enums.VehicleType;
import com.example.geektrust.onboard.OnBoard;
import com.example.geektrust.onboard.impl.VehicleOnBoard;

public class AddVehicleCommand implements CommandInterface {

    OnBoard<Vehicle> vehicleOnBoard = VehicleOnBoard.getInstance();
    @Override
    public String execute(String[] variables) {
        assert variables.length == 5;
        String branchId = variables[1];
        VehicleType vehicleType =VehicleType.forValue(variables[2]);
       String vehicleId = variables[3];
        Double price = Double.parseDouble(variables[4]);
        Vehicle build = Vehicle.builder().branchId(branchId).vehicleType(vehicleType).id(vehicleId).vehicalStatus(VehicalStatus.AVAILABLE).price(price).build();
       if(vehicleOnBoard.onBoard(build)) {      return "TRUE";
    }else {
        return "FALSE";
    } }

}
