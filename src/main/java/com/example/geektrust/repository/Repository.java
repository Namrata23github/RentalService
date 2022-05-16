package com.example.geektrust.repository;

import java.util.List;
import java.util.Map;

public interface Repository<T, S> {

     T storeInventory(T object);
     List<T> getByCriteria(S criteria);
}
