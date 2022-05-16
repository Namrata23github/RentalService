package com.example.geektrust.onboard.impl;

import com.example.geektrust.criteria.BranchSearchCriteria;
import com.example.geektrust.criteria.VehicleSearchCriteria;
import com.example.geektrust.dao.Branch;
import com.example.geektrust.dao.Vehicle;
import com.example.geektrust.enums.VehicleType;
import com.example.geektrust.repository.Repository;
import com.example.geektrust.repository.impl.BranchRepositoryImpl;
import com.example.geektrust.repository.impl.VehicleRepository;
import com.example.geektrust.onboard.OnBoard;

import java.util.List;

public class VehicleOnBoard implements OnBoard<Vehicle> {

    private static volatile VehicleOnBoard instance;
    private static Object mutex = new Object();

    private VehicleOnBoard() {
    }

    public static VehicleOnBoard getInstance() {
        VehicleOnBoard result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new VehicleOnBoard();
            }
        }
        return result;
    }
    Repository<Vehicle, VehicleSearchCriteria> vehicleRepository = VehicleRepository.getInstance();
    Repository<Branch, BranchSearchCriteria> branchRepository = BranchRepositoryImpl.getInstance();

    @Override
    public boolean onBoard(Vehicle object) {
        try {

            BranchSearchCriteria branchSearchCriteria = BranchSearchCriteria.builder().name(object.getBranchId()).build();

            List<Branch> byCriteria = branchRepository.getByCriteria(branchSearchCriteria);

            if(!byCriteria.get(0).getVehicleTypes().contains(object.getVehicleType())) {
                throw new  NoSuchMethodException();
            }
            vehicleRepository.storeInventory(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
