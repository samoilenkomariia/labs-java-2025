import controller.ShapeController;
import model.Circle;
import model.Rectangle;
import model.ShapeModel;
import model.Triangle;
import view.ShapeView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter number of shapes:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        ShapeView view = new ShapeView();
        ShapeModel model = new ShapeModel(n);
        ShapeController controller = new ShapeController(view, model);

        while (true) {
            view.printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    controller.displayShapes();
                    break;
                case "2":
                    controller.printTotalArea();
                    break;
                case "3":
                    controller.printTotalAreaByType(Circle.class);
                    break;
                case "4":
                    controller.printTotalAreaByType(Rectangle.class);
                    break;
                case "5":
                    controller.printTotalAreaByType(Triangle.class);
                    break;
                case "6":
                    controller.sortByArea();
                    break;
                case "7":
                    controller.sortByColor();
                    break;
                case "8":
                    String file = "shapes.txt";
                    System.out.println("Enter file path or press enter to choose default");
                    String input = scanner.nextLine();
                    if (!input.isEmpty()) {
                        file = input;
                    }
                    controller.saveDataToFile(file);
                    break;
                case "9":
                    String filePath = "shapes.txt";
                    System.out.println("Enter file path or press enter to choose default");
                    String inputF = scanner.nextLine();
                    if (!inputF.isEmpty()) {
                        filePath = inputF;
                    }
                    controller.readDataFromFile(filePath);
                    break;
                case "0":
                    scanner.close();
                    System.exit(0);
                default:
                    view.printMessage("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}