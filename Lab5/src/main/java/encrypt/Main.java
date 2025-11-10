package encrypt;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String DEFAULT_SOURCE_TEXT = "src/main/java/encrypt/text.txt";
    private static final String DEFAULT_ENCRYPTED_FILE = "src/main/java/encrypt/encrypted.txt";
    private static final String DEFAULT_DECRYPTED_FILE = "src/main/java/encrypt/decrypted.txt";

    public static void main(String[] args) {
        char key = 'k';

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                case "1":
                    encryptAndPrint(scanner, key);
                    break;
                case "2":
                    encryptAndSave(scanner, key);
                    break;
                case "3":
                    decryptAndPrint(scanner, key);
                    break;
                case "4":
                    decryptAndSave(scanner, key);
                    break;
                case "5":
                    String input = scanner.next();
                    if (!input.isEmpty()) key = input.charAt(0);
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.print("""
                
                0. Exit
                1. Encrypt file and print to console
                2. Encrypt file and save to new file
                3. Decrypt file and print to console
                4. Decrypt file and save to new file
                5. Choose a key
                Enter your choice: 
                """);
    }

    private static void encryptAndPrint(Scanner scanner, char key) {
        String sourceFile = getFilePath(scanner, "Enter source file to encrypt", DEFAULT_SOURCE_TEXT);

        try (Reader input = new Encryptor(new FileReader(sourceFile), key)) {
            String encryptedData = processStream(input);
            System.out.println("Successfully encrypted data");
            System.out.println(encryptedData);
        } catch (FileNotFoundException e) {
            System.err.printf("Error: File not found at %s%n", sourceFile);
        } catch (IOException e) {
            System.err.printf("Error reading or encrypting file: %s%n", e.getMessage());
        }
    }

    private static void encryptAndSave(Scanner scanner, char key) {
        String sourceFile = getFilePath(scanner, "Enter source file to encrypt", DEFAULT_SOURCE_TEXT);
        String destFile = getFilePath(scanner, "Enter destination file for encrypted data", DEFAULT_ENCRYPTED_FILE);

        try (Reader input = new Encryptor(new FileReader(sourceFile), key)) {
            String encryptedData = processStream(input);
            writeToFile(destFile, encryptedData);
            System.out.printf("Successfully encrypted data and saved to %s%n", destFile);
        } catch (FileNotFoundException e) {
            System.err.printf("Error: Source file not found at %s%n", sourceFile);
        } catch (IOException e) {
            System.err.printf("Error processing or writing file: %s%n", e.getMessage());
        }
    }

    private static void decryptAndPrint(Scanner scanner, char key) {
        String sourceFile = getFilePath(scanner, "Enter source file to decrypt", DEFAULT_ENCRYPTED_FILE);

        try (Reader input = new Decryptor(new FileReader(sourceFile), key)) {
            String decryptedData = processStream(input);
            System.out.println("Successfully decrypted data");
            System.out.println(decryptedData);
        } catch (FileNotFoundException e) {
            System.err.printf("Error: File not found at '%s'%n", sourceFile);
        } catch (IOException e) {
            System.err.printf("Error reading or decrypting file: %s%n", e.getMessage());
        }
    }

    private static void decryptAndSave(Scanner scanner, char key) {
        String sourceFile = getFilePath(scanner, "Enter source file to decrypt", DEFAULT_ENCRYPTED_FILE);
        String destFile = getFilePath(scanner, "Enter destination file for decrypted data", DEFAULT_DECRYPTED_FILE);

        try (Reader input = new Decryptor(new FileReader(sourceFile), key)) {
            String decryptedData = processStream(input);
            writeToFile(destFile, decryptedData);
            System.out.printf("Successfully decrypted data and saved to '%s'%n", destFile);
        } catch (FileNotFoundException e) {
            System.err.printf("Error: Source file not found at '%s'%n", sourceFile);
        } catch (IOException e) {
            System.err.printf("Error processing or writing file: %s%n", e.getMessage());
        }
    }


    private static String getFilePath(Scanner scanner, String prompt, String defaultValue) {
        System.out.printf("%s (default: %s):%n", prompt, defaultValue);
        String path = scanner.nextLine();
        if (path == null || path.trim().isEmpty()) {
            return defaultValue;
        }
        return path;
    }

    private static String processStream(Reader reader) throws IOException {
        StringBuilder data = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            data.append((char) c);
        }
        return data.toString();
    }

    private static void writeToFile(String filePath, String data) throws IOException {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(filePath))) {
            output.write(data);
        }
    }
}