package controller;
import javax.swing.JOptionPane;
import view.ExpenseTrackerView;
import controller.InputValidation;
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
        if (!InputValidation.isValidCategory(categoryFilter)) {
            return new ArrayList<>();} // Return an empty list for invalid category
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
