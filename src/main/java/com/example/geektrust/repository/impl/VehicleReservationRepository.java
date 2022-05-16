package com.example.geektrust.repository.impl;

import com.example.geektrust.criteria.VehicleReservationSearchCriteria;
import com.example.geektrust.dao.VehicleReservation;
import com.example.geektrust.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleReservationRepository implements Repository<VehicleReservation, VehicleReservationSearchCriteria> {

    private static volatile VehicleReservationRepository instance;
    private static Object mutex = new Object();

    private VehicleReservationRepository() {
    }

    public static VehicleReservationRepository getInstance() {
        VehicleReservationRepository result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new VehicleReservationRepository();
            }
        }
        return result;
    }

    List<VehicleReservation> vehicleReservations = new ArrayList<>();

    @Override
    public VehicleReservation storeInventory(VehicleReservation vehicleReservation) {
        vehicleReservations.add(vehicleReservation);
        return vehicleReservation;
    }

    @Override
    public List<VehicleReservation> getByCriteria(VehicleReservationSearchCriteria criteria) {
        List<String> vehicleIds = criteria.getVehicleIds();
        return   vehicleReservations.stream().filter(vehicleReservation -> vehicleIds.contains(vehicleReservation.getVehicleId())).collect(Collectors.toList());
    }
}
