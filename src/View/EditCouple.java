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

    private JCheckBox waltzBox;
    private JCheckBox tangoBox;
    private JCheckBox foxtrotBox;
    private JCheckBox vienneseBox;

    private JCheckBox lChaChaBox;
    private JCheckBox sambaBox;
    private JCheckBox lrumbaBox;
    private JCheckBox pasoBox;
    private JCheckBox jiveBox;

    private JCheckBox sWaltzBox;
    private JCheckBox sTangoBox;
    private JCheckBox sVienneseBox;
    private JCheckBox sFoxtrotBox;
    private JCheckBox quickstepBox;

    public EditCouple() {

        JLabel leadLabel = new JLabel("Lead Name: ");
        leadLabel.setFont(new Font("Ariel", Font.PLAIN, 16));
        JLabel followLabel = new JLabel("Follow Name: ");
        followLabel.setFont(new Font("Ariel", Font.PLAIN, 16));

        leadField = new JTextField(15);
        followField = new JTextField(15);

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
        gridBagConstraints.ipadx = 15;

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

        // ----- Rhythm checks ----------
        JLabel rhythmText = new JLabel("Rhythm");
        rhythmText.setFont(new Font("Ariel", Font.PLAIN, 16));
        rhythmText.setBorder(new EmptyBorder(10,0,0,0));

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        add(rhythmText, gridBagConstraints);

        chaChaBox = new JCheckBox("Cha Cha", false);
        chaChaBox.setBorder(new EmptyBorder(0,15,0,0));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
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


        // ---- Smooth checks ----------
        JLabel smoothText = new JLabel("Smooth");
        smoothText.setFont(new Font("Ariel", Font.PLAIN, 16));

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        add(smoothText, gridBagConstraints);

        waltzBox = new JCheckBox("Waltz", false);
        waltzBox.setBorder(new EmptyBorder(0,15,0,0));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        add(waltzBox, gridBagConstraints);

        tangoBox = new JCheckBox("Tango", false);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        add(tangoBox, gridBagConstraints);

        foxtrotBox = new JCheckBox("Foxtrot", false);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        add(foxtrotBox, gridBagConstraints);

        vienneseBox = new JCheckBox("Viennese", false);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        add(vienneseBox, gridBagConstraints);


        // ----- Latin checks ---------
        JLabel latinText = new JLabel("Latin");
        latinText.setFont(new Font("Ariel", Font.PLAIN, 16));

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        add(latinText, gridBagConstraints);

        lChaChaBox = new JCheckBox("Cha Cha", false);
        lChaChaBox.setBorder(new EmptyBorder(0,15,0,0));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        add(lChaChaBox, gridBagConstraints);

        sambaBox = new JCheckBox("Samba", false);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        add(sambaBox, gridBagConstraints);

        lrumbaBox = new JCheckBox("Rumba", false);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        add(lrumbaBox, gridBagConstraints);

        pasoBox = new JCheckBox("Paso Doble", false);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        add(pasoBox, gridBagConstraints);

        jiveBox = new JCheckBox("Jive", false);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        add(jiveBox, gridBagConstraints);


        // ------ Standard checks ---------
        JLabel standardText = new JLabel("Standard");
        standardText.setFont(new Font("Ariel", Font.PLAIN, 16));

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        add(standardText, gridBagConstraints);

        sWaltzBox = new JCheckBox("Waltz", false);
        sWaltzBox.setBorder(new EmptyBorder(0,15,0,0));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        add(sWaltzBox, gridBagConstraints);

        sTangoBox = new JCheckBox("Tango", false);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        add(sTangoBox, gridBagConstraints);

        sVienneseBox = new JCheckBox("Viennese Waltz", false);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        add(sVienneseBox, gridBagConstraints);

        sFoxtrotBox = new JCheckBox("Foxtrot", false);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        add(sFoxtrotBox, gridBagConstraints);

        quickstepBox = new JCheckBox("Quickstep", false);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        add(quickstepBox, gridBagConstraints);

        //-------- navigation buttons -----------
        addButton = new JButton("Add");
        viewButton = new JButton("View All");

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = buttonInset;

        add(addButton, gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
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

    public void submitUsers(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void viewUsers(ActionListener actionListener) {
        viewButton.addActionListener(actionListener);
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

    public boolean getWaltz() {
        return waltzBox.isSelected();
    }
    public boolean getTango() {
        return tangoBox.isSelected();
    }
    public boolean getFoxtrot() {
        return foxtrotBox.isSelected();
    }
    public boolean getViennese() {
        return vienneseBox.isSelected();
    }

    public boolean getLatinCha() {
        return lChaChaBox.isSelected();
    }
    public boolean getSamba() {
        return sambaBox.isSelected();
    }
    public boolean getLatinRumba() {
        return lrumbaBox.isSelected();
    }
    public boolean getPaso() {
        return pasoBox.isSelected();
    }
    public boolean getJive() { return jiveBox.isSelected();}

    public boolean getStandardWaltz() {
        return sWaltzBox.isSelected();
    }
    public boolean getStandardTango() {
        return sTangoBox.isSelected();
    }
    public boolean getStandardViennese() {
        return sVienneseBox.isSelected();
    }
    public boolean getStandardFoxtrot() {
        return sFoxtrotBox.isSelected();
    }
    public boolean getQuickstep() { return quickstepBox.isSelected();}

    // reset fields
    public void reset(boolean bln) {
        if(bln) {
            leadField.setText("");
            followField.setText("");

            // reset all boxes when reloading page
//            chaChaBox.setSelected(false);
//            rumbaBox.setSelected(false);
//            swingBox.setSelected(false);
//            boleroBox.setSelected(false);
//            mamboBox.setSelected(false);
//
//            waltzBox.setSelected(false);
//            tangoBox.setSelected(false);
//            foxtrotBox.setSelected(false);
//            vienneseBox.setSelected(false);
//
//            lChaChaBox.setSelected(false);
//            sambaBox.setSelected(false);
//            lrumbaBox.setSelected(false);
//            pasoBox.setSelected(false);
//            jiveBox.setSelected(false);
//
//            sWaltzBox.setSelected(false);
//            sTangoBox.setSelected(false);
//            sVienneseBox.setSelected(false);
//            sFoxtrotBox.setSelected(false);
//            quickstepBox.setSelected(false);
        }
    }
}
