import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple Java program to read CSV file in Java. In this program we will read
 * list of books stored in CSV file as comma separated values.
 * 
 * @author WINDOWS 8
 *
 */
public class FileReader {


    public static List<Couple> readCouplesFromCSV(String fileName) {
        List<Couple> couples = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");

                Couple couple = createCouple(attributes);

                // adding book into ArrayList
                couples.add(couple);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return couples;
    }

    private static Couple createCouple(String[] metadata) {
        String lead = metadata[0]; // lead name
        String follow = metadata[1]; // follow name
        List<Dance> dances = new ArrayList<>();
        
        // 2 is waltz, 3 is tango, 4 is foxtrot, 5 is cha cha, 6 is rumba, 7 is swing
        // if there is an X there, this couple is dancing that dance
        if(metadata.length >= 3 && metadata[2].equals("X")){
            dances.add(Dance.WALTZ);
        }
        if(metadata.length >= 4 && metadata[3].equals("X")){
            dances.add(Dance.TANGO);
        }
        if(metadata.length >= 5 && metadata[4].equals("X")){
            dances.add(Dance.FOXTROT);
        }
        if(metadata.length >= 6 && metadata[5].equals("X")){
            dances.add(Dance.CHACHA);
        }
        if(metadata.length >= 7 && metadata[6].equals("X")){
            dances.add(Dance.RUMBA);
        }
        if(metadata.length >= 8 && metadata[7].equals("X")){
            dances.add(Dance.SWING);
        }

        return new Couple(lead, follow, dances);
    }

}
