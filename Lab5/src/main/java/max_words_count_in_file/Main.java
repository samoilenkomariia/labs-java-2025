package max_words_count_in_file;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String DEFAULT_FILE_PATH = "src/main/java/max_words_count_in_file/text.txt";

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    System.out.print("Exiting...");
                    scanner.close();
                    System.exit(0);
                case "1":
                    handleReadFromFile();
                    break;
                case "2":
                    handleReadFromConsole();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.print("""
                0. Exit
                1. Read from file
                2. Read from console
                """);
    }

    private static void handleReadFromFile() {
        System.out.println("Enter file name to read from (default: " + DEFAULT_FILE_PATH + "):");
        String file = scanner.nextLine();
        String path;
        if (file.isEmpty()) {
            path = DEFAULT_FILE_PATH;
        } else {
            path = file;
        }

        try {
            String winner = WordCounter.getMaxCountOfWordsInFile(path);
            System.out.printf("Line with max number of words: %s%n", winner);
        } catch (IOException e) {
            System.err.printf("Error reading from file: %s%n", e.getMessage());
        }
    }

    private static void handleReadFromConsole() {
        System.out.println("Type text (type -1 in new line to finish):");
        StringBuilder data = new StringBuilder();
        String input = scanner.nextLine();

        while (!input.equals("-1")) {
            data.append(input);
            data.append(System.lineSeparator());
            input = scanner.nextLine();
        }

        String winner = WordCounter.getMaxCountOfWordsInText(data.toString());
        System.out.printf("Line with max number of words: %s%n", winner);
    }
}