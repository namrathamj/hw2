package controller;

import view.ExpenseTrackerView;
import controller.InputValidation;
import java.util.List;
import java.util.ArrayList;


import model.ExpenseTrackerModel;
import model.Transaction;


public class FilterByAmount implements FilterStrategy {
    private double amountFilter;

    public FilterByAmount(double amountFilter) {
        this.amountFilter = amountFilter;
    }

    @Override
    public ArrayList<Integer> filter(List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        ArrayList<Integer> selectedTransactionIndexes = new ArrayList<>();

        if (!InputValidation.isValidAmount(amountFilter)) {
            return new ArrayList<>(); // Return an empty list for invalid category
        }
        

        for(int i=0; i < transactions.size(); i++) {
          Transaction t= transactions.get(i);
          if (t.getAmount()<(amountFilter)) {
            filteredTransactions.add(t);
            selectedTransactionIndexes.add(i);
          }
        }
        return selectedTransactionIndexes;
    }
}

