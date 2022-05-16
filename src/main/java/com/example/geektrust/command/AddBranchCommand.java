package com.example.geektrust.command;

import com.example.geektrust.dao.Address;
import com.example.geektrust.dao.Branch;
import com.example.geektrust.enums.VehicleType;
import com.example.geektrust.onboard.OnBoard;
import com.example.geektrust.onboard.impl.BranchOnBoard;

import java.util.ArrayList;
import java.util.List;

public class AddBranchCommand implements CommandInterface {

    OnBoard<Branch> branchOnBoard = BranchOnBoard.getInstance();

    @Override
    public String execute(String[] variables) {
        assert variables.length == 3;

        String branchId = variables[1];

        String car[] = variables[2].split(",");
        List<VehicleType> vehicleTypes = new ArrayList<>();
        for(int i = 0; i < car.length; i++) {
            vehicleTypes.add(VehicleType.forValue(car[i]));
        }
        Address address = Address.builder().street(branchId + "address").build();
        Branch build = Branch.builder().name(branchId).address(address).vehicleTypes(vehicleTypes).build();
        if(branchOnBoard.onBoard(build)) {
            return "TRUE";
        }else {
            return "FALSE";
        }
    }
}
