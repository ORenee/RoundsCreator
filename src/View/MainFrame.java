package View;

import Controller.CoupleController;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

public class MainFrame extends JFrame {

    // Card layout for switching view
    private CardLayout cardLayout;

    public MainFrame() {
        super("Rounds Creator");
        cardLayout = new CardLayout();
        EditCouple form = new EditCouple();
        ViewCouples viewCouples = new ViewCouples();
        ViewResults viewResults = new ViewResults();
        // sets our layout as a card layout
        setLayout(cardLayout);

        // initialize user controller
        new CoupleController(form, viewCouples, viewResults);

        // adds view to card layout with unique constraints
        add(form, "form");
        add(viewCouples, "view couples");
        add(viewResults, "view results");

        // switch view according to its constraints on click
        form.viewUsers(e -> cardLayout.show(MainFrame.this.getContentPane(), "view couples"));
        viewCouples.backButton(e -> cardLayout.show(MainFrame.this.getContentPane(), "form"));
        viewCouples.createRoundsButton(e -> cardLayout.show(MainFrame.this.getContentPane(), "view results"));
        viewResults.backButton(e -> cardLayout.show(MainFrame.this.getContentPane(), "view couples"));
        viewResults.runAgain(e -> cardLayout.show(MainFrame.this.getContentPane(), "view couples")); // TODO: change to home page

        // icon for our application
        ImageIcon imageIcon = new ImageIcon("src/assets/appicon.png");
        setIconImage(imageIcon.getImage());
        // frame width & height
        int FRAME_WIDTH = 1200;
        int FRAME_HEIGHT = 700;
        // size of our application frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
