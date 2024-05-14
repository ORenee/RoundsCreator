package View;

import Model.Round;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ViewResults extends JPanel {

    // Table for user data
    private JTable userTable;
    // table column
    private String[] userTableColumn = {"HEAT NUMBER", "DANCE", "DANCERS"};

    // back button
    private JButton backButton;

    // create rounds button
    private JButton runAgainButton;

    public ViewResults() {
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // toolbar for buttons
        JToolBar toolBar = new JToolBar();
        userTable = new JTable();
        userTable.setRowHeight(20);
        userTable.setIntercellSpacing(new Dimension(10,2));
        // scroll bar for table
        JScrollPane userTableScroll = new JScrollPane(userTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        backButton = new JButton("Go Back");
        runAgainButton = new JButton("Run Again");
        add(toolBar);
        toolBar.add(backButton);
        toolBar.add(runAgainButton);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        add(userTableScroll);

    }

    //TODO: sometimes new couples added are not in rounds list when generating it immediately after adding

    // gets couples from database and loads to table
    public void setRounds(List<Round> rounds) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) userTable.getModel();
        defaultTableModel.setColumnIdentifiers(userTableColumn);
        defaultTableModel.setRowCount(0); // remove current rows to get new data

        int heatNumber = 1;
        for(Round round : rounds){
            String[][] results = round.formatForDisplay(heatNumber);
            for(String[] heat : results){
                defaultTableModel.addRow(heat);
            }
            // set next heat number
            heatNumber = Integer.parseInt(results[results.length - 1][0]) + 1;
        }

        resizeColumnWidth(10, 20, 70);
        userTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    // event listener for create rounds button
    public void runAgain(ActionListener actionListener) {
        runAgainButton.addActionListener(actionListener);
    }

    private void resizeColumnWidth(double... percentages) {
        final TableColumnModel columnModel = userTable.getColumnModel();
        int totalWidth = getToolkit().getScreenSize().width - 350;
        double total = Arrays.stream(percentages).sum();

        for (int column = 0; column < userTable.getColumnCount(); column++) {
            int width = (int)(totalWidth * (percentages[column] / total)); // Min width based on percentage
            for (int row = 0; row < userTable.getRowCount(); row++) {
                TableCellRenderer renderer = userTable.getCellRenderer(row, column);
                Component comp = userTable.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > totalWidth)
                width=totalWidth;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}

