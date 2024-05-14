package Controller;

import Model.CreateRounds;
import Model.Dance;
import Model.Database;
import View.EditCouple;
import View.ViewCouples;
import View.ViewResults;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CoupleController {
    // database file
    private String databaseFile = "src\\data\\database.txt";
    private Database database;
    private EditCouple form;
    private ViewCouples viewCouples;
    private ViewResults viewResults;

    public CoupleController(EditCouple form, ViewCouples viewCouples, ViewResults viewResults) {
        this.database = new Database();
        this.form = form;
        this.viewCouples = viewCouples;
        this.viewResults = viewResults;

        // submit user
        this.form.submitUsers(e -> {
            String leadname = this.form.getFirstname().trim();
            String followname = this.form.getLastname().trim();

            List<Dance> dances = createDancesList();

            // simple validations
            if(leadname.isEmpty()) {
                JOptionPane.showMessageDialog(this.form, "Lead required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if(followname.isEmpty()) {
                JOptionPane.showMessageDialog(this.form, "Follow required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if(dances.isEmpty()){
                JOptionPane.showMessageDialog(this.form, "Please select at lease one dance.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            this.database.addCouple(leadname, followname, dances);
            this.database.saveUser(new File(databaseFile));
            this.form.reset(true);
        });

        // load couples
        this.form.viewUsers(e -> {
            this.viewCouples.getCouples(this.database.loadUsers(new File(databaseFile)));
        });

        // create rounds
        this.viewCouples.createRoundsButton(e -> {
            this.viewResults.setRounds(CreateRounds.getRounds(this.database.loadUsers(new File(databaseFile))));
        });

    }

    private List<Dance> createDancesList(){
        List<Dance> dances = new ArrayList<>();
        if(this.form.getChaCha()) { dances.add(Dance.CHACHA); }
        if(this.form.getRumba()) { dances.add(Dance.RUMBA); }
        if(this.form.getSwing()) { dances.add(Dance.SWING); }
        if(this.form.getBolero()) { dances.add(Dance.BOLERO); }
        if(this.form.getMambo()) { dances.add(Dance.MAMBO); }

        if(this.form.getWaltz()) { dances.add(Dance.WALTZ); }
        if(this.form.getTango()) { dances.add(Dance.TANGO); }
        if(this.form.getFoxtrot()) { dances.add(Dance.FOXTROT); }
        if(this.form.getViennese()) { dances.add(Dance.VIENNESE); }

        if(this.form.getLatinCha()) { dances.add(Dance.LCHACHA); }
        if(this.form.getSamba()) { dances.add(Dance.SAMBA); }
        if(this.form.getLatinRumba()) { dances.add(Dance.LRUMBA); }
        if(this.form.getPaso()) { dances.add(Dance.PASODOBLE); }
        if(this.form.getJive()) { dances.add(Dance.JIVE); }

        if(this.form.getStandardWaltz()) { dances.add(Dance.SWALTZ); }
        if(this.form.getStandardTango()) { dances.add(Dance.STANGO); }
        if(this.form.getStandardViennese()) { dances.add(Dance.SVIENNESE); }
        if(this.form.getStandardFoxtrot()) { dances.add(Dance.SFOXTROT); }
        if(this.form.getQuickstep()) { dances.add(Dance.SQUICKSTEP); }

        return dances;
    }
}
