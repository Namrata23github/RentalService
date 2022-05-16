package com.example.geektrust.repository.impl;

import com.example.geektrust.criteria.BranchSearchCriteria;
import com.example.geektrust.exceptions.InventoryNotFoundExceptions;
import com.example.geektrust.repository.Repository;
import com.example.geektrust.dao.Branch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.geektrust.errors.ExtendedErrorMessage.INVENTORY_NOT_FOUND;

/*
To store onboarded branch
 */
public class BranchRepositoryImpl implements Repository<Branch, BranchSearchCriteria> {

    private static volatile BranchRepositoryImpl instance;
    private static Object mutex = new Object();

    private BranchRepositoryImpl() {
    }

    public static BranchRepositoryImpl getInstance() {
        BranchRepositoryImpl result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new BranchRepositoryImpl();
            }
        }
        return result;
    }

    Map<String, Branch> onBoardedBraches = new HashMap<>();

    @Override
    public Branch storeInventory(Branch branch) {
        return onBoardedBraches.put(branch.getName(), branch);
    }

    @Override
    public List<Branch> getByCriteria(BranchSearchCriteria criteria) {
        String name = criteria.getName();
        if (onBoardedBraches.isEmpty() || !onBoardedBraches.containsKey(name)) {
            throw  new InventoryNotFoundExceptions(INVENTORY_NOT_FOUND, String.format(INVENTORY_NOT_FOUND.getMessage(), "Branch" , name) );
        }
        return Arrays.asList(onBoardedBraches.get(name));
    }
}
