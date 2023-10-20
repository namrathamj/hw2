package view;
import javax.swing.JComboBox;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;
import view.HighlightTableCellRenderer;
import controller.InputValidation;
import java.util.ArrayList;
import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  
  //ADDING HERE
  private JButton addFilterBtn;
  private JButton addFilterBtnCat;
  private JFormattedTextField filter_amt_input;
  private JTextField filter_choices;
 
  //ADDING HERE
  private ArrayList<Integer> selectedTransactionIndexes = new ArrayList<>();
  public void setSelectedTransactionIndexes(ArrayList<Integer> selectedTransactionIndexes) {
      this.selectedTransactionIndexes = selectedTransactionIndexes;
  }

  public void setupCategoryColumnRenderer() {
    transactionsTable.setDefaultRenderer(Object.class, new HighlightTableCellRenderer(selectedTransactionIndexes));
  
  }

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(800, 600); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");
    //ADDING HERE
    addFilterBtn = new JButton("FilterByAmt(<)");
    addFilterBtnCat = new JButton("FilterByCat");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);
    filter_choices = new JTextField(10);
    // Create table
    transactionsTable = new JTable(model);
  

    //ADDING HERE 
    JLabel filter_label = new JLabel("Filter By");
    JLabel filter_amt_label = new JLabel("Amount");
    filter_label.setVisible(true);
    filter_amt_label.setVisible(true);
    NumberFormat format2 = NumberFormat.getNumberInstance();
    filter_amt_input = new JFormattedTextField(format2);
    filter_amt_input.setColumns(5);

    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
    //ADDING HERE
    buttonPanel.add(addFilterBtn);
    buttonPanel.add(addFilterBtnCat);

    //ADDING HERE
    JPanel filterPanel = new JPanel();
    filterPanel.add(filter_label);
    filterPanel.add(filter_choices);
    filterPanel.add(filter_amt_label);
    filterPanel.add(filter_amt_input);    
 
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
    //ADDING HERE
    add(filterPanel, BorderLayout.EAST);
  
    // Set frame properties
    setSize(600, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  

  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  //ADDING HERE
  public JButton getAddFilterBtn() {
    return addFilterBtn;
  }
 
  public JButton getAddFilterBtnCat() {
    return addFilterBtnCat;
  }

  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  public String getFilterChoices() {
    return filter_choices.getText();
  }
 
  
  public double getFilterAmountInput() {
    String filterAmtInputText = filter_amt_input.getText();
    double filter_amt_input = Double.parseDouble(filterAmtInputText);

    return filter_amt_input;
  }

 public void setfilter_amt_input(JFormattedTextField filter_amt_input) {
    this.filter_amt_input = filter_amt_input;
  }
}
