package com.example.geektrust.repository.impl;

import com.example.geektrust.criteria.VehicleSearchCriteria;
import com.example.geektrust.dao.Vehicle;
import com.example.geektrust.exceptions.InventoryNotFoundExceptions;
import com.example.geektrust.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.geektrust.errors.ExtendedErrorMessage.INVENTORY_NOT_FOUND;

public class VehicleRepository implements Repository<Vehicle, VehicleSearchCriteria> {

    private static volatile VehicleRepository instance;
    private static Object mutex = new Object();

    private VehicleRepository() {
    }

    public static VehicleRepository getInstance() {
        VehicleRepository result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new VehicleRepository();
            }
        }
        return result;
    }

    Map<String, Set<Vehicle>> onBoardedVehicleByBranch = new HashMap<>();


    @Override
    public Vehicle storeInventory(Vehicle vehicle) {
        String branchId = vehicle.getBranchId();
        Set<Vehicle> vehicleList = onBoardedVehicleByBranch.getOrDefault(branchId, new HashSet<>());
        vehicleList.add(vehicle);
         onBoardedVehicleByBranch.put(branchId, vehicleList);
         return vehicle;
    }

    @Override
    public List<Vehicle> getByCriteria(VehicleSearchCriteria criteria) {
        String branch = criteria.getBranchName();
        if (onBoardedVehicleByBranch.isEmpty() || !onBoardedVehicleByBranch.containsKey(branch)) {
            throw  new InventoryNotFoundExceptions(INVENTORY_NOT_FOUND, String.format(INVENTORY_NOT_FOUND.getMessage(), "Vehicle" , branch) );
        }
        return new ArrayList<>(onBoardedVehicleByBranch.get(branch));
    }
}
