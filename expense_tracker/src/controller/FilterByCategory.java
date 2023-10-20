package controller;

import view.ExpenseTrackerView;

import java.util.List;
import java.util.ArrayList;


import model.ExpenseTrackerModel;
import model.Transaction;

public class FilterByCategory implements FilterStrategy {
    private String categoryFilter;

    public FilterByCategory(String categoryFilter) {
        this.categoryFilter = categoryFilter;
    }

    @Override
    public ArrayList<Integer> filter(List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        ArrayList<Integer> selectedTransactionIndexes = new ArrayList<>();
        for (int i=0; i< transactions.size(); i++) {
            Transaction t= transactions.get(i);
            if (t.getCategory().equals(categoryFilter)) {
                filteredTransactions.add(t);
                selectedTransactionIndexes.add(i);
            }
        }
        return selectedTransactionIndexes;
    }
}

