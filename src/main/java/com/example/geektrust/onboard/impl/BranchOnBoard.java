package com.example.geektrust.onboard.impl;

import com.example.geektrust.criteria.BranchSearchCriteria;
import com.example.geektrust.dao.Branch;
import com.example.geektrust.onboard.OnBoard;
import com.example.geektrust.repository.Repository;
import com.example.geektrust.repository.impl.BranchRepositoryImpl;


public class BranchOnBoard implements OnBoard<Branch> {

    private static volatile BranchOnBoard instance;
    private static Object mutex = new Object();

    private BranchOnBoard() {
    }

    public static BranchOnBoard getInstance() {
        BranchOnBoard result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new BranchOnBoard();
            }
        }
        return result;
    }
    Repository<Branch, BranchSearchCriteria> branchRepository = BranchRepositoryImpl.getInstance();

    @Override
    public boolean onBoard(Branch branch) {
        try {
            branchRepository.storeInventory(branch);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}