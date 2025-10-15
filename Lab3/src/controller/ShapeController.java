package controller;

import model.*;
import view.ShapeView;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ShapeController {

    private final Shape[] shapes;
    private final ShapeView view;

    public ShapeController(ShapeView view, int numOfShapes) {
        this.view = view;
        this.shapes = generateShapes(numOfShapes);
    }

    public ShapeController(ShapeView view, Shape[] shapes) {
        this.view = view;
        this.shapes = shapes;
    }

    public void displayShapes() {
        view.printShapes(shapes);
    }

    public void printTotalArea() {
        double total = 0;
        for (var s : shapes) {
            total += s.calcArea();
        }
        view.printTotalArea(total);
    }

    public void printTotalAreaByType(Class<? extends Shape> shapeClass) {
        double total = 0;
        for (var s : shapes) {
            if (shapeClass.isInstance(s)) {
                total += s.calcArea();
            }
        }
        view.printTotalAreaByType(shapeClass.getSimpleName(), total);
    }

    public void sortByArea() {
        var copy = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(copy, Comparator.comparingDouble(Shape::calcArea).reversed());
        view.printMessage("Sorted by Area");
        view.printShapes(copy);
    }

    public void sortByColor() {
        var copy = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(copy, Comparator.comparing(Shape::getShapeColor));
        view.printMessage("Sorted by Color");
        view.printShapes(copy);
    }

    private Shape[] generateShapes(int count) {
        Shape[] result = new Shape[count];
        String[] colors = {"red", "blue", "green", "yellow", "black", "white"};
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int shapeType = random.nextInt(3);
            String color = colors[random.nextInt(colors.length)];
            double dim1 = random.nextDouble() * 10 + random.nextDouble(10);
            double dim2 = random.nextDouble() * 10 + random.nextDouble(10);
            double dim3 = random.nextDouble() * 10 + random.nextDouble(10);

            switch (shapeType) {
                case 0 -> result[i] = new Rectangle(color, dim1, dim2);
                case 1 -> result[i] = new Triangle(color, dim1, dim2, dim3);
                case 2 -> result[i] = new Circle(color, dim1);
            }
        }
        return result;
    }
}
