import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;
import controller.FilterByCategory;
import controller.FilterByAmount;
import controller.FilterStrategy;

public class ExpenseTrackerApp {
 
  public static void main(String[] args) {
  
    // Create MVC Components
    ExpenseTrackerModel model =  new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);
 
    //Initialize view
    view.setVisible(true);

    //Handle add transaction button
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();

      // call controller to add transaction
      boolean added = controller.addTransaction(amount, category);

      if (!added)
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      });

    //Handler for Filter by amount 
    view.getAddFilterBtn().addActionListener(e -> {
      // get amount to filter with 
      double filter_amt_input = view.getFilterAmountInput();
      FilterStrategy amountFilterStrategy = new FilterByAmount(filter_amt_input);
      boolean filteredamt = controller.filter(amountFilterStrategy);
      if (!filteredamt){
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront(); }   
    });

   //Handler for Filter by Category
   view.getAddFilterBtnCat().addActionListener(e -> {
     // get category to filter with 
     String category_filter = view.getFilterChoices();
     FilterStrategy categoryFilterStrategy = new FilterByCategory(category_filter);
     boolean filteredcat = controller.filter(categoryFilterStrategy);
     if (!filteredcat){
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();}
      });

 
  } 
}
