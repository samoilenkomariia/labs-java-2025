package internationalization;

import logging.Decryptor;
import logging.Encryptor;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static final String DEFAULT_SOURCE_TEXT = "src/main/java/logging/text.txt";
    private static final String DEFAULT_ENCRYPTED_FILE = "src/main/java/logging/encrypted.txt";
    private static final String DEFAULT_DECRYPTED_FILE = "src/main/java/logging/decrypted.txt";

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static ResourceBundle bundle;

    public static void main(String[] args) {
        setupLogging();
        Locale currentLocale = new Locale("en", "US");
        bundle = ResourceBundle.getBundle("location.text", currentLocale);
        logger.info("Application started");
        char key = 'k';
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            logger.fine("User selected option: " + choice);

            switch (choice) {
                case "0":
                    System.out.println(bundle.getString("msg.exit"));
                    logger.info("Application stopped by user");
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
                    changeKey(scanner);
                    break;
                case "6":
                    changeLanguage(scanner);
                    break;
                default:
                    System.out.println(bundle.getString("msg.invalid"));
                    logger.warning("User entered invalid menu choice: " + choice);
                    break;
            }
        }
    }

    private static void changeLanguage(Scanner scanner) {
        System.out.println(bundle.getString("msg.lang_prompt"));
        String langChoice = scanner.nextLine();
        Locale newLocale;
        if ("2".equals(langChoice)) {
            newLocale = new Locale("uk", "UA");
        } else {
            newLocale = new Locale("en", "US");
        }
        bundle = ResourceBundle.getBundle("location.text", newLocale);
        logger.info("Language changed to: " + newLocale.getLanguage());
    }

    private static void changeKey(Scanner scanner) {
        System.out.println(bundle.getString("prompt.key"));
        String input = scanner.next();
        if (!input.isEmpty()) {
            System.out.println(bundle.getString("msg.key_changed"));
            logger.info("Encryption key changed by user");
        }
        scanner.nextLine();
    }

    private static void setupLogging() {
        try {
            LogManager.getLogManager().reset();
            FileHandler fileHandler = new FileHandler("application_log.txt", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return "[" + record.getLevel() + "] " + record.getMessage() + "\n";
                }
            });

            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Failed to initialize logging system: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println(bundle.getString("menu.0"));
        System.out.println(bundle.getString("menu.1"));
        System.out.println(bundle.getString("menu.2"));
        System.out.println(bundle.getString("menu.3"));
        System.out.println(bundle.getString("menu.4"));
        System.out.println(bundle.getString("menu.5"));
        System.out.println(bundle.getString("menu.6")); // Пункт зміни мови
        System.out.print(bundle.getString("menu.choice") + " ");
    }

    private static void encryptAndPrint(Scanner scanner, char key) {
        String sourceFile = getFilePath(scanner, "prompt.source", DEFAULT_SOURCE_TEXT);
        logger.fine("Processing file: " + sourceFile);

        try (Reader input = new Encryptor(new FileReader(sourceFile), key)) {
            String encryptedData = processStream(input);

            logger.info("Successfully encrypted data (console output)");
            System.out.println(bundle.getString("msg.success_encrypt_print"));
            System.out.println(encryptedData);

        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File not found: " + sourceFile, e);
            System.err.println(bundle.getString("error.file_not_found") + " " + sourceFile);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Error", e);
            System.err.println(bundle.getString("error.io") + " " + e.getMessage());
        }
    }

    private static void encryptAndSave(Scanner scanner, char key) {
        String sourceFile = getFilePath(scanner, "prompt.source", DEFAULT_SOURCE_TEXT);
        String destFile = getFilePath(scanner, "prompt.dest", DEFAULT_ENCRYPTED_FILE);

        try (Reader input = new Encryptor(new FileReader(sourceFile), key)) {
            String encryptedData = processStream(input);
            writeToFile(destFile, encryptedData);

            logger.info("Encrypted data saved to " + destFile);
            System.out.println(bundle.getString("msg.success_encrypt_save") + " " + destFile);

        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Source file not found", e);
            System.err.println(bundle.getString("error.file_not_found") + " " + sourceFile);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Error", e);
            System.err.println(bundle.getString("error.io") + " " + e.getMessage());
        }
    }

    private static void decryptAndPrint(Scanner scanner, char key) {
        String sourceFile = getFilePath(scanner, "prompt.source", DEFAULT_ENCRYPTED_FILE);

        try (Reader input = new Decryptor(new FileReader(sourceFile), key)) {
            String decryptedData = processStream(input);

            logger.info("Successfully decrypted data");
            System.out.println(bundle.getString("msg.success_decrypt_print"));
            System.out.println(decryptedData);

        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File not found", e);
            System.err.println(bundle.getString("error.file_not_found") + " " + sourceFile);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Error", e);
            System.err.println(bundle.getString("error.io") + " " + e.getMessage());
        }
    }

    private static void decryptAndSave(Scanner scanner, char key) {
        String sourceFile = getFilePath(scanner, "prompt.source", DEFAULT_ENCRYPTED_FILE);
        String destFile = getFilePath(scanner, "prompt.dest", DEFAULT_DECRYPTED_FILE);

        try (Reader input = new Decryptor(new FileReader(sourceFile), key)) {
            String decryptedData = processStream(input);
            writeToFile(destFile, decryptedData);

            logger.info("Decrypted data saved to " + destFile);
            System.out.println(bundle.getString("msg.success_decrypt_save") + " " + destFile);

        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Source file not found", e);
            System.err.println(bundle.getString("error.file_not_found") + " " + sourceFile);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Error", e);
            System.err.println(bundle.getString("error.io") + " " + e.getMessage());
        }
    }

    private static String getFilePath(Scanner scanner, String promptKey, String defaultValue) {
        System.out.println(bundle.getString(promptKey) + " (default: " + defaultValue + "):");
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