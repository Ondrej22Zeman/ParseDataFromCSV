import com.opencsv.CSVWriter;
import org.apache.commons.lang3.StringUtils;

import javax.swing.text.html.HTMLWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class SaveFile {
    private final ArrayList<RowData> rows;
    private final String chosenYear;
    private final String chosenQuarter;
    private String chosenVendor;
    private double totalUnits = 0;
    private final String sortMethod;

    public SaveFile(ArrayList<RowData> rows, String chosenYear, String chosenQuarter, String chosenVendor, String sortMethod) {
        this.rows = rows;
        this.chosenYear = chosenYear;
        this.chosenQuarter = chosenQuarter;
        this.chosenVendor = chosenVendor;
        this.sortMethod = sortMethod;
    }

    public void createSaveFile(ArrayList<String[]> list) throws IOException {
        File file = new File("out/file.html");

        FileWriter fileWriter = new FileWriter(file);


        fileWriter.write("""
                <!DOCTYPE html>
                <html>
                <body>
                <table style="text-align: center">
                <tr style="background-color: grey">
                <th style="width: 140px">Vendor</th>
                <th style="width: 140px">Units</th>
                <th style="width: 140px">Share</th>
                </tr>""");

        int i = 0;
        for (String[] row : list) {
            if (i == list.size()-1){
                fileWriter.write("<tr style=\"background-color: yellow\">" + "\n");
            }else{
                fileWriter.write("<tr>" + "\n");
            }
            fileWriter.write("<td>" + row[0] + "</td>" + "\n");
            fileWriter.write("<td>" + row[1] + "</td>" + "\n");
            fileWriter.write("<td>" + row[2] + "</td>" + "\n");
            fileWriter.write("</tr>"+ "\n");
            i++;
        }
        fileWriter.write("""
                </table>
                </body>
                </html>""");
        fileWriter.close();
    }

    public ArrayList<String[]> createArray(){
        ArrayList<String[]> filteredList = new ArrayList<>();


        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);


        for (RowData row : rows) {
            if (Objects.equals(row.getYear(), chosenYear) && Objects.equals(row.getQuarter(), chosenQuarter)){
                boolean duplicityVendor = false;
                for (String[] filteredRow : filteredList) {
                    if (Objects.equals(filteredRow[0], row.getVendor())){
                        filteredRow[1] = String.valueOf(
                                Double.parseDouble(row.getUnits()) +
                                Double.parseDouble(filteredRow[1])
                        );
                        duplicityVendor = true;
                        break;
                    }
                }
                totalUnits += Double.parseDouble(row.getUnits());
                if (duplicityVendor) continue;
                filteredList.add(new String[]{row.getVendor(), row.getUnits(), ""});
            }
        }

        for (String[] row : filteredList) {
            row[2] = (df.format(((Double.parseDouble(row[1])/totalUnits) * 100)) + "%");
        }






        if (Objects.equals(sortMethod, "Alphabetically")){
            filteredList.sort(new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {

                    return o1[0].compareTo(o2[0]);
                }
            });
        }

        if (Objects.equals(sortMethod, "Numerically")){
            filteredList.sort(new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    Double firstToCompare = Double.parseDouble(o1[1]);
                    Double secondToCompare = Double.parseDouble(o2[1]);
                    return secondToCompare.compareTo(firstToCompare);
                }
            });
        }

        for (String[] row :
                filteredList) {
            row[1] = df.format(Double.parseDouble(row[1]));
            System.out.println(Arrays.toString(row));
        }

        if (chosenVendor != null){
            String[] vendorInformation = null;
            int i = 0;
            for (String[] row: filteredList) {
                if (Objects.equals(row[i], chosenVendor)){
                    vendorInformation = row;
                    break;
                }
                i++;
            }
            System.out.println(i+1 + ". row contains information about " + chosenVendor);
            assert vendorInformation != null;
            System.out.println(chosenVendor + " sold " + vendorInformation[1] + " units, and their share was " + vendorInformation[2]);
        }

        filteredList.add(new String[]{"Total", df.format(totalUnits), "100%"});

        return filteredList;

    }
}
