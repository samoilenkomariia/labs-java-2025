import org.xml.sax.InputSource;

import java.util.Scanner;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        MyTranslator translator = new MyTranslator();
        translator.addWord("hello", "привіт");
        translator.addWord("i", "я");
        translator.addWord("love", "люблю");
        translator.addWord("riding a bike", "їздити на велосипеді");
        translator.addWord("is", "є");
        translator.addWord("my", "моє");
        translator.addWord("hobby", "хобі");
        var r1 = translator.translate("hello i love riding a bike");
        var r2 = translator.translate("riding a bike is my hobby");
        System.out.println(r1 + "\n" + r2);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String input = scanner.nextLine();
            switch(input) {
                case "0":
                    scanner.close();
                    System.exit(0);
                    break;
                case "1":
                    addDataToTranslator(translator, scanner::nextLine);
                    break;
                case "2":
                    translateInputData(translator, scanner::nextLine);
                    break;
                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
    }

    private static void addDataToTranslator(MyTranslator t, Supplier<String> in) {
        while (true) {
            System.out.println("Write down the sentence in English. Press enter to stop");
            String eng = in.get().trim();
            if (eng.isEmpty()) return;
            System.out.println("Write down the sentence translation in Ukrainian. Press enter to stop");
            String ukr =  in.get().trim();
            if (ukr.isEmpty()) return;
            t.addWord(eng, ukr);
        }
    }

    private static void translateInputData(MyTranslator t, Supplier<String> in) {
        while (true) {
            System.out.println("Write down the sentence in English. Press enter to stop");
            String input = in.get().trim();
            if (input.isEmpty()) return;
            System.out.println(t.translate(input));
        }
    }

    private static void printMenu() {
        System.out.print("""
                Choose an option:
                0 - exit
                1 - add data to dictionary
                2 - translate
                """);
    }
}
