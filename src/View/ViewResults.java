package View;

import Model.Round;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
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
        userTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
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
    }

    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    // event listener for create rounds button
    public void runAgain(ActionListener actionListener) {
        runAgainButton.addActionListener(actionListener);
    }
}
