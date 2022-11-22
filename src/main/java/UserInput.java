import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class UserInput {
    public SaveFile getUserInput(String filePath){
        Scanner scanner = new Scanner(System.in);
        CSVParser csvParser = new CSVParser();
        UniqueValues uniqueValues = new UniqueValues();
        ArrayList<RowData> rows = csvParser.parseFile(filePath);
        Set<String> uniqueYears = uniqueValues.getUniqueYears(rows);
        Set<String> uniqueQuarters = uniqueValues.getUniqueQuarters(rows);
        Set<String> uniqueVendors = uniqueValues.getUniqueVendors(rows);

        String chosenVendor = null;
        String chosenYear;
        String chosenQuarter;


        System.out.println("What year would you like to print?");
        System.out.print("Available years: ");

        for (String year :
                uniqueYears) {
            System.out.print(year + "  ");
        }
        System.out.println();
        System.out.print("Year: ");
        chosenYear = scanner.nextLine();
        while (!uniqueYears.contains(chosenYear)){
            System.out.print("Invalid year, please choose again: ");
            chosenYear = scanner.nextLine();
        }

        System.out.println("What quarter would you like to print?");
        System.out.print("Available quarters: ");

        for (String quarter :
                uniqueQuarters) {
            System.out.print(quarter + "  ");
        }
        System.out.println();
        System.out.print("Quarter: ");
        chosenQuarter = scanner.nextLine();
        while (!uniqueQuarters.contains(chosenQuarter)){
            System.out.print("Invalid quarter, please choose again: ");
            chosenQuarter = scanner.nextLine();
        }

        System.out.println("Would you like to print row of chosen vendor? Yes/No");
        String rowOfVendor = scanner.nextLine();
        while(!(rowOfVendor.equals("No") || rowOfVendor.equals("Yes"))) {
            System.out.print("Invalid input, please answer again: ");
            rowOfVendor = scanner.nextLine();
        }
        if (rowOfVendor.equals("Yes")){
            System.out.print("Available vendors: ");
            for (String vendor :
                    uniqueVendors) {
                System.out.print(vendor + "  ");
            }
            System.out.println();
            System.out.print("Vendor: ");
            chosenVendor = scanner.nextLine();
            while (!uniqueVendors.contains(chosenVendor)){
                System.out.print("Please input a valid vendor: ");
                chosenVendor = scanner.nextLine();
            }
        }

        System.out.println("How would you like to sort table? Alphabetically/Numerically");
        String sortMethod = scanner.nextLine();

        while (!Objects.equals(sortMethod, "Alphabetically") && !Objects.equals(sortMethod, "Numerically")){
            System.out.print("Please input a valid answer: ");
            sortMethod = scanner.nextLine();
        }
        return new SaveFile(rows, chosenYear, chosenQuarter, chosenVendor, sortMethod);
    }
}
