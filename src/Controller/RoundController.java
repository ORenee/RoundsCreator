package Controller;

import Model.Dance;
import Model.Database;
import View.EditCouple;
import View.ViewCouples;
import View.ViewResults;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RoundController {
    // database file
    private String databaseFile = "src\\data\\database.txt";
    private Database database;
    private ViewResults viewResults;

    public RoundController(ViewResults viewResults) {
        this.database = new Database();
        this.viewResults = viewResults;



    }
}
