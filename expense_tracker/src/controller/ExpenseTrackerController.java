package controller;

import view.ExpenseTrackerView;

import java.util.List;
import java.util.ArrayList;


import model.ExpenseTrackerModel;
import model.Transaction;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }


  // Adding a filter method to be used by FilterStrategy interface 
  public boolean filter(FilterStrategy filterStrategy) {
    List<Transaction> allTransactions = model.getTransactions();
    ArrayList<Integer> selectedTransactionIndexes = filterStrategy.filter(allTransactions);
    // Setting selected transactions to green
    view.setSelectedTransactionIndexes(selectedTransactionIndexes);
    view.setupCategoryColumnRenderer();
    refresh();
    return true;
    }

  
  // Other controller methods 
}
