package view;
import model.Shape;

public class ShapeView {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printShapes(Shape... shapes) {
        if (shapes == null || shapes.length == 0) {
            return;
        }
        for (Shape shape : shapes) {
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
}
