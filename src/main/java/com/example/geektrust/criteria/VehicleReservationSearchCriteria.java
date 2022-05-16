package com.example.geektrust.criteria;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VehicleReservationSearchCriteria {
     List<String> vehicleIds;
}
