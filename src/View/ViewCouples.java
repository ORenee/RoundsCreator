package View;

import Model.Couple;
import Model.Dance;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ViewCouples extends JPanel {

    // Table for user data
    private DefaultTableModel defaultTableModel;
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
        userTable.setDefaultEditor(Object.class, null);
        userTable.setRowHeight(20);
        userTable.setRowSelectionAllowed(true);

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
        defaultTableModel = (DefaultTableModel) userTable.getModel();
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

        resizeColumnWidth(20, 20, 60);
//        userTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        userTable.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
    }

    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    // event listener for create rounds button
    public void createRoundsButton(ActionListener actionListener) {
        createRoundsButton.addActionListener(actionListener);
    }

    public void editCouple(ListSelectionListener actionListener) {
        ListSelectionModel selectionModel = userTable.getSelectionModel();
        selectionModel.addListSelectionListener(actionListener);
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
