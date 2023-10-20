package view;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.*;

public class HighlightTableCellRenderer extends DefaultTableCellRenderer {
    private ArrayList<Integer> selectedTransactionIndexes;

    public HighlightTableCellRenderer(ArrayList<Integer> selectedTransactionIndexes) {
        this.selectedTransactionIndexes = selectedTransactionIndexes;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (selectedTransactionIndexes.contains(row)) {
            c.setBackground(new Color(173, 255, 168)); // Light green for selected transactions
        } else {
            c.setBackground(table.getBackground());
        }
        return c;
    }
}

