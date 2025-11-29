import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Scanner;

public class Reflection {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String literal = "Java";
        System.out.println("Before reflection: Java");
        modifyString(literal, "Code");
        System.out.println("After reflection: " + literal);

        System.out.print("Reflection consequence Java => ");
        System.out.println("Java");

        while (true) {
            System.out.print("Enter initial string (enter 0 to quit): ");
            String inputString = scanner.nextLine();
            if (Objects.equals(inputString, "0")) break;
            System.out.print("Enter new value: ");
            String newContent = scanner.nextLine();
            System.out.println("\nBefore reflection: " + inputString);
            modifyString(inputString, newContent);
            System.out.println("After reflection: " + inputString);
        }

        scanner.close();
    }

    private static void modifyString(String target, String newValue) {
        try {
            Class<?> stringClass = String.class;
            Field valueField = stringClass.getDeclaredField("value");
            valueField.setAccessible(true);
            Object newBytes = valueField.get(newValue);
            valueField.set(target, newBytes);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("Reflection error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}