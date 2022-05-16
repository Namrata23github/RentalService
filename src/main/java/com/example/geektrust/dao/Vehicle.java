package com.example.geektrust.dao;

import com.example.geektrust.enums.VehicalStatus;
import com.example.geektrust.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vehicle {
    String id;
    Double price;
    VehicleType vehicleType;
    VehicalStatus vehicalStatus;
    String branchId;


    // Overriding equals() to compare two Complex objects
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Vehicle)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Vehicle v = (Vehicle) o;

        // Compare the data members and return accordingly
        return id.equals(v.id);
    }
}
