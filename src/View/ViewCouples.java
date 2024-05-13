package View;

import Model.Couple;
import Model.Dance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class ViewCouples extends JPanel {

    // Table for user data
    private JTable userTable;
    // table column
    private String[] userTableColumn = {"LEAD NAME", "FOLLOW NAME", "DANCES"};

    // back button
    private JButton backButton;

    // create rounds button
    private JButton createRoundsButton;

    public ViewCouples() {
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
        createRoundsButton = new JButton("Create Rounds");
        add(toolBar);
        toolBar.add(backButton);
        toolBar.add(createRoundsButton);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        add(userTableScroll);

    }

    // gets couples from database and loads to table
    public void getCouples(List<Couple> couples) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) userTable.getModel();
        defaultTableModel.setColumnIdentifiers(userTableColumn);
        defaultTableModel.setRowCount(0); // remove current rows to get new data

        for(Couple couple : couples){
            String[] row = new String[3];
            row[0] = couple.getLead();
            row[1] = couple.getFollow();
            row[2] = couple.getDances()
                .stream()
                .map(Dance::getName)
                .collect(Collectors.joining(", "));

            defaultTableModel.addRow(row);
        }
    }

    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    // event listener for create rounds button
    public void createRoundsButton(ActionListener actionListener) {
        createRoundsButton.addActionListener(actionListener);
    }
}
