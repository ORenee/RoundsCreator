package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private ArrayList<Couple> coupleArrayList;

    // Data in file is always listed in
    // Heat number, Lead, Follow,
    // Rhythm dances in order as Xs, Smooth in order, Latin in order, Standard in order

    public Database() {
        coupleArrayList = new ArrayList<>();
    }

    // adds user to our collection
    public void addCouple(String lead, String follow, List<Dance> dances) {
        int id = IdGenerator.getInstance().getUniqueId();
        coupleArrayList.add(new Couple(lead, follow, dances, id));
    }

    // saves user to database file
    public void saveUser(File file) {
        try {
            // user model
            Couple couple;
            String save_data = "";

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            int i = 0;
            while( i < coupleArrayList.size()) {
                couple = coupleArrayList.get(i);
                save_data = couple.getId() + "," + couple.getLead() + "," + couple.getFollow();
                save_data += setDancesForDB(couple.getDances());
                i++;
            }
            bufferedWriter.write(save_data);
            bufferedWriter.newLine();
            // prevents memory leak
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads user from database file
    public List<Couple> loadUsers(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file));
            // each lines to array
//            objects = bufferedReader.lines().toArray();
//            bufferedReader.close();
//            return objects;
            String line = bufferedReader.readLine();

            List<Couple> couples = new ArrayList<>();
            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");

                Couple couple = createCouple(attributes);

                couples.add(couple);


                // read next line before looping
                // if end of file reached, line would be null
                line = bufferedReader.readLine();
            }
            return couples;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String setDancesForDB(List<Dance> dances){
        String save_data = "";
        // rhythm
        save_data += dances.contains(Dance.CHACHA) ? ",X" : ",";
        save_data += dances.contains(Dance.RUMBA) ? ",X" : ",";
        save_data += dances.contains(Dance.SWING) ? ",X" : ",";
        save_data += dances.contains(Dance.BOLERO) ? ",X" : ",";
        save_data += dances.contains(Dance.MAMBO) ? ",X" : ",";

        // smooth
        save_data += dances.contains(Dance.WALTZ) ? ",X" : ",";
        save_data += dances.contains(Dance.TANGO) ? ",X" : ",";
        save_data += dances.contains(Dance.FOXTROT) ? ",X" : ",";
        save_data += dances.contains(Dance.VIENNESE) ? ",X" : ",";

        // latin
        save_data += dances.contains(Dance.LCHACHA) ? ",X" : ",";
        save_data += dances.contains(Dance.SAMBA) ? ",X" : ",";
        save_data += dances.contains(Dance.LRUMBA) ? ",X" : ",";
        save_data += dances.contains(Dance.PASODOBLE) ? ",X" : ",";
        save_data += dances.contains(Dance.JIVE) ? ",X" : ",";

        // standard
        save_data += dances.contains(Dance.SWALTZ) ? ",X" : ",";
        save_data += dances.contains(Dance.STANGO) ? ",X" : ",";
        save_data += dances.contains(Dance.SVIENNESE) ? ",X" : ",";
        save_data += dances.contains(Dance.SFOXTROT) ? ",X" : ",";
        save_data += dances.contains(Dance.SQUICKSTEP) ? ",X" : ",";

        return save_data;
    }

    private static Couple createCouple(String[] metadata) {
        int id = Integer.parseInt(metadata[0]);
        String lead = metadata[1]; // lead name
        String follow = metadata[2]; // follow name
        List<Dance> dances = new ArrayList<>();

        // starting at [3], dances are listed rhythm, smooth, latin, standard
        // if there is an X there, this couple is dancing that dance

        // the length of the data should always be as much as we need
        // but if the user inputs a badly formatted csv, we need to make
        // sure there are no out of bounds errors

        // rhythm
        if(metadata.length > 3 && metadata[3].equals("X")){
            dances.add(Dance.CHACHA);
        }
        if(metadata.length > 4 && metadata[4].equals("X")){
            dances.add(Dance.RUMBA);
        }
        if(metadata.length > 5 && metadata[5].equals("X")){
            dances.add(Dance.SWING);
        }
        if(metadata.length > 6 && metadata[6].equals("X")){
            dances.add(Dance.BOLERO);
        }
        if(metadata.length > 7 && metadata[7].equals("X")){
            dances.add(Dance.MAMBO);
        }

        // smooth
        if(metadata.length > 8 && metadata[8].equals("X")){
            dances.add(Dance.WALTZ);
        }
        if(metadata.length > 9 && metadata[9].equals("X")){
            dances.add(Dance.TANGO);
        }
        if(metadata.length > 10 && metadata[10].equals("X")){
            dances.add(Dance.FOXTROT);
        }
        if(metadata.length > 11 && metadata[11].equals("X")){
            dances.add(Dance.VIENNESE);
        }

        // latin
        if(metadata.length > 12 && metadata[12].equals("X")){
            dances.add(Dance.LCHACHA);
        }
        if(metadata.length > 13 && metadata[13].equals("X")){
            dances.add(Dance.SAMBA);
        }
        if(metadata.length > 14 && metadata[14].equals("X")){
            dances.add(Dance.LRUMBA);
        }
        if(metadata.length > 15 && metadata[15].equals("X")){
            dances.add(Dance.PASODOBLE);
        }
        if(metadata.length > 16 && metadata[16].equals("X")){
            dances.add(Dance.JIVE);
        }

        // standard
        if(metadata.length > 17 && metadata[17].equals("X")){
            dances.add(Dance.SWALTZ);
        }
        if(metadata.length > 18 && metadata[18].equals("X")){
            dances.add(Dance.STANGO);
        }
        if(metadata.length > 19 && metadata[19].equals("X")){
            dances.add(Dance.SVIENNESE);
        }
        if(metadata.length > 20 && metadata[20].equals("X")){
            dances.add(Dance.SFOXTROT);
        }
        if(metadata.length > 21 && metadata[21].equals("X")){
            dances.add(Dance.SQUICKSTEP);
        }

        return new Couple(lead, follow, dances, id);
    }

}
