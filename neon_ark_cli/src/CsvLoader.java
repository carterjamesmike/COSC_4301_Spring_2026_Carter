import java.io.*;
import java.util.*;

public class CsvLoader {

    public static List<Warden> loadWardens(String fileName) {

        List<Warden> wardens = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                Warden warden = new Warden(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6],
                        data[7],
                        data[8]
                );

                wardens.add(warden);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("ERROR: Unable to load CSV file.");
        }

        return wardens;
    }
}