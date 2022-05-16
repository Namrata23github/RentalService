package com.example.geektrust.booking;

import com.example.geektrust.criteria.BranchSearchCriteria;
import com.example.geektrust.criteria.VehicleReservationSearchCriteria;
import com.example.geektrust.criteria.VehicleSearchCriteria;
import com.example.geektrust.dao.Branch;
import com.example.geektrust.dao.VehicleReservation;
import com.example.geektrust.dao.Vehicle;
import com.example.geektrust.enums.VehicalStatus;
import com.example.geektrust.model.Booking;
import com.example.geektrust.repository.Repository;
import com.example.geektrust.repository.impl.BranchRepositoryImpl;
import com.example.geektrust.repository.impl.VehicleReservationRepository;
import com.example.geektrust.repository.impl.VehicleRepository;
import com.example.geektrust.utils.BookingUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingService {

    private static volatile BookingService instance;
    private static Object mutex = new Object();

    private BookingService() {
    }

    public static BookingService getInstance() {
        BookingService result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new BookingService();
            }
        }
        return result;
    }

    Repository<Vehicle, VehicleSearchCriteria> vehicleRepository = VehicleRepository.getInstance();
    Repository<VehicleReservation, VehicleReservationSearchCriteria> vehicalReservationRepository = VehicleReservationRepository.getInstance();
    Repository<Branch, BranchSearchCriteria> branchRepository = BranchRepositoryImpl.getInstance();

    public double bookVehicle(BookingServiceStrategy serviceStrategy, Booking booking) {

        try {
            BranchSearchCriteria branchSearchCriteria = BranchSearchCriteria.builder().name(booking.getBranchName()).build();
            Branch branchByName = branchRepository.getByCriteria(branchSearchCriteria).get(0);

            if(!branchByName.getVehicleTypes().contains(booking.getVehicleType())) {
                throw new NoSuchElementException();
            }

            VehicleSearchCriteria vehicleSearchCriteria = VehicleSearchCriteria.builder().branchName(booking.getBranchName()).build();
            List<Vehicle> byCriteria = vehicleRepository.getByCriteria(vehicleSearchCriteria);

            VehicleReservationSearchCriteria vehicleReservationSearchCriteria = VehicleReservationSearchCriteria.builder().vehicleIds(byCriteria.stream().map(Vehicle::getId).collect(Collectors.toList())).build();
            List<VehicleReservation> vehicalReservations = vehicalReservationRepository.getByCriteria(vehicleReservationSearchCriteria);

            boolean isDemand = BookingUtils.isDemand(byCriteria);


            Vehicle vehicle = serviceStrategy.bookVehicle(booking, vehicalReservations, byCriteria.stream().filter(v -> v.getVehicleType().
                    equals(booking.getVehicleType())).collect(Collectors.toList()));


            //We can make this thread safe to avoid race conditions
            vehicle.setVehicalStatus(VehicalStatus.BOOKED);
            vehicleRepository.storeInventory(vehicle);



            Double vehiclePrice = vehicle.getPrice() * (booking.getEndTime().getHour() - booking.getStartTime().getHour());

            if (isDemand) {
                vehiclePrice = vehiclePrice + (vehiclePrice + 0.10);
            }
            UUID reservationId = UUID.randomUUID();
            VehicleReservation vehicleReservation = VehicleReservation.builder()
                    .reservationId(reservationId)
                    .vehicleId(vehicle.getId())
                    .reservationTime(LocalDateTime.now())
                    .dropLocation(branchByName.getAddress())
                    .startTime(booking.getStartTime())
                    .endTime(booking.getEndTime())
                    .vehicleType(booking.getVehicleType())
                    .totalPrice(vehiclePrice)
                    .build();

            vehicalReservationRepository.storeInventory(vehicleReservation);
            List<UUID> vehicalReservationsByBranch = branchByName.getVehicalReservations();
            if(Objects.isNull(vehicalReservationsByBranch)) {
                vehicalReservationsByBranch = new ArrayList<>();
            }
            vehicalReservationsByBranch.add(reservationId);
            branchByName.setVehicalReservations(vehicalReservationsByBranch);
            branchRepository.storeInventory(branchByName);
            return vehiclePrice;
        } catch (Exception e) {
            return -1;
        }

    }

    public String displayAvailableVehicles(Booking booking) {
        VehicleSearchCriteria vehicleSearchCriteria = VehicleSearchCriteria.builder().branchName(booking.getBranchName()).build();
        List<Vehicle> byCriteria = vehicleRepository.getByCriteria(vehicleSearchCriteria);



        VehicleReservationSearchCriteria vehicleReservationSearchCriteria = VehicleReservationSearchCriteria.builder().vehicleIds(byCriteria.stream().map(Vehicle::getId).collect(Collectors.toList())).build();
        List<VehicleReservation> vehicalReservations = vehicalReservationRepository.getByCriteria(vehicleReservationSearchCriteria);

        List<String> availableBookedVehicleInGivenTimeSlot = BookingUtils.getAvailableBookedVehicleInGivenTimeSlot(booking, vehicalReservations);
        return byCriteria.stream()
                .filter(vehicle -> vehicle.getVehicalStatus().equals(VehicalStatus.AVAILABLE) ||
                        availableBookedVehicleInGivenTimeSlot.contains(vehicle.getId())).map(vehicle -> vehicle.getId())
                .collect(Collectors.joining(","));





    }
}
