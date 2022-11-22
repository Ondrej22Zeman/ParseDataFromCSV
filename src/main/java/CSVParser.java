import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CSVParser {
    public ArrayList<RowData> parseFile(String filePath){
        ArrayList<RowData> rows = new ArrayList<>();
        String[] yearAndQuarter;
        try{
            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] nextline = reader.readNext();
            while(nextline != null){
                yearAndQuarter = nextline[1].split(" ");

                if (Objects.equals(nextline[0], "Country")){
                    nextline = reader.readNext();
                    continue;
                }

                rows.add(new RowData(nextline[0], yearAndQuarter[0], yearAndQuarter[1], nextline[2], nextline[3]));
                nextline = reader.readNext();
            }

            HashMap<RowData,Integer> hashmap = new HashMap<RowData,Integer>();

            for (int j = 0; j < rows.size(); j++) {
                hashmap.put(rows.get(j), j);
            }
        }catch (FileNotFoundException e){
            System.out.println(
                    "---------------------------" + "\n"
                    + "Couldn't find a file!" + "\n"
                    + "---------------------------");
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }

        return rows;
    }
}
