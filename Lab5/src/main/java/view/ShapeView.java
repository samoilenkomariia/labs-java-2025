package view;
import model.Drawable;

public class ShapeView {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printShapes(Drawable[] shapes) {
        if (shapes == null || shapes.length == 0) {
            return;
        }
        for (var shape : shapes) {
            shape.draw();
        }
        System.out.println("_".repeat(80));
    }

    public void printTotalArea(double totalArea) {
        printMessage(String.format("Total area: %.2f\n", totalArea));
    }

    public void printTotalAreaByType(String type, double area) {
        printMessage(String.format("%s total area: %.2f\n", type, area));
    }

    public void printMenu() {
        printMessage("1. Display all shapes");
        printMessage("2. Display total area of all shapes");
        printMessage("3. Display total area of Circles");
        printMessage("4. Display total area of Rectangles");
        printMessage("5. Display total area of Triangles");
        printMessage("6. Sort and display shapes by Area");
        printMessage("7. Sort and display shapes by Color");
        printMessage("8. Save to file");
        printMessage("9. Read from file");
        printMessage("0. Exit");
    }
}
