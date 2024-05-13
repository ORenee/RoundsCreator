package Model;

//import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.net.URL;
import java.util.List;

public class CSVFileWriter {

    public static void createOutput(List<Round> rounds) throws Exception {
        URL fileUrl = CSVFileWriter.class.getClassLoader().getResource("output.csv");

        //CSVWriter writer = new CSVWriter(new FileWriter(fileUrl.getFile()));

        int heatNumber = 1;
        for(Round round : rounds){

            String[][] records = round.printString(heatNumber);

            for(String[] record : records){

                //Write the record to file
                //writer.writeNext(record, false);
            }

            heatNumber += 3;

        }

        //close the writer
        //writer.close();
    }
}