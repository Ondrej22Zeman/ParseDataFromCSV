public class main {
    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        SaveFile saveFile = userInput.getUserInput("src/main/resources/data.csv");

        saveFile.createArray();
    }
}
