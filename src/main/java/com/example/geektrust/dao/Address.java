package com.example.geektrust.dao;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {
    private String street;
    private String city;
    private String state;
    private String pincode;
}
