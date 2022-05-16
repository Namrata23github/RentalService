package com.example.geektrust.dao;

import com.example.geektrust.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Branch {
    String name;
    List<VehicleType> vehicleTypes;
    List<UUID> vehicalReservations;
    Address address;

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Branch)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Branch b = (Branch) o;

        // Compare the data members and return accordingly
        return name.equals(b.name);
    }
}
