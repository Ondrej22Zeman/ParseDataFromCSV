import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        UserInput userInput = new UserInput();
        SaveFile saveFile = userInput.getUserInput("src/main/resources/data.csv");

        ArrayList<String[]> list = saveFile.createArray();

        saveFile.createSaveFile(list);
    }
}
