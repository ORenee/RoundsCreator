package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditCouple extends JPanel {

    private JTextField leadField;
    private JTextField followField;

    private JButton addButton;
    private JButton viewButton;

    private JCheckBox chaChaBox;
    private JCheckBox rumbaBox;
    private JCheckBox swingBox;
    private JCheckBox boleroBox;
    private JCheckBox mamboBox;

    public EditCouple() {

        JLabel leadLabel = new JLabel("Lead Name: ");
        JLabel followLabel = new JLabel("Follow Name: ");

        leadField = new JTextField(25);
        followField = new JTextField(25);

        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(278, 40));
        viewButton = new JButton("View All Users");
        viewButton.setPreferredSize(new Dimension(278, 40));

        // space between fields
        Insets fieldsInset = new Insets(0, 0, 10, 0);
        // space between buttons
        Insets buttonInset = new Insets(20,0,0,0);

        // uses Grid Bag Layout
        // 0,0 is upper left corner
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = fieldsInset;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        // lead textbox
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        add(leadLabel, gridBagConstraints); // add lead label to 0,0

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;

        add(leadField, gridBagConstraints);

        // follow textbox
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        add(followLabel, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;

        add(followField, gridBagConstraints);

        // rhythm checks
        JLabel rhythmText = new JLabel("Rhythm");
        rhythmText.setFont(new Font("Ariel", Font.PLAIN, 16));

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        add(rhythmText, gridBagConstraints);

        chaChaBox = new JCheckBox("Cha Cha", false);
        chaChaBox.setBorder(new EmptyBorder(0,10,0,0));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        add(chaChaBox, gridBagConstraints);

        rumbaBox = new JCheckBox("Rumba", false);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        add(rumbaBox, gridBagConstraints);

        swingBox = new JCheckBox("Swing", false);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        add(swingBox, gridBagConstraints);

        boleroBox = new JCheckBox("Bolero", false);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        add(boleroBox, gridBagConstraints);

        mamboBox = new JCheckBox("Mambo", false);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        add(mamboBox, gridBagConstraints);


        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = buttonInset;

        add(addButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = buttonInset;

        add(viewButton, gridBagConstraints);
    }

    // getters
    public String getFirstname() {
        return leadField.getText();
    }

    public String getLastname() {
        return followField.getText();
    }

    public boolean getChaCha() {
        return chaChaBox.isSelected();
    }
    public boolean getRumba() {
        return rumbaBox.isSelected();
    }
    public boolean getSwing() {
        return swingBox.isSelected();
    }
    public boolean getBolero() {
        return boleroBox.isSelected();
    }
    public boolean getMambo() { return mamboBox.isSelected();}

    public void submitUsers(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void viewUsers(ActionListener actionListener) {
        viewButton.addActionListener(actionListener);
    }

    // reset fields
    public void reset(boolean bln) {
        if(bln) {
            leadField.setText("");
            followField.setText("");
        }
    }
}
