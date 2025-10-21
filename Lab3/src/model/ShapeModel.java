package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ShapeModel {

    private final Shape[] shapes;

    public ShapeModel(Shape[] shapes) {
        this.shapes = shapes;
    }

    public ShapeModel(int numOfShapes) {
        this.shapes = generateShapes(numOfShapes);
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public double calcTotalArea() {
        double total = 0;
        for (var s : shapes) {
            total += s.calcArea();
        }
        return total;
    }

    public double calcTotalAreaByType(Class<? extends Shape> shapeClass) {
        double total = 0;
        for (var s : shapes) {
            if (shapeClass.isInstance(s)) {
                total += s.calcArea();
            }
        }
        return total;
    }

    public Shape[] getShapesSortedByArea() {
        var copy = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(copy, Comparator.comparingDouble(Shape::calcArea));
        return copy;
    }

    public Shape[] getShapesSortedByColor() {
        var copy = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(copy, Comparator.comparing(Shape::getShapeColor));
        return copy;
    }

    private Shape[] generateShapes(int count) {
        enum ShapeType { RECTANGLE, TRIANGLE, CIRCLE };
        ShapeType[] allShapeTypes = ShapeType.values();
        Shape[] result = new Shape[count];
        String[] colors = {"red", "blue", "green", "yellow", "black", "white"};
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            ShapeType shapeType = allShapeTypes[random.nextInt(allShapeTypes.length)];
            String color = colors[random.nextInt(colors.length)];

            switch (shapeType) {
                case RECTANGLE -> {
                    double dim1 = random.nextDouble() * 10 + random.nextDouble(10);
                    double dim2 = random.nextDouble() * 10 + random.nextDouble(10);
                    result[i] = new Rectangle(color, dim1, dim2);
                }
                case TRIANGLE -> {
                    double dim1 = random.nextDouble() * 10 + random.nextDouble(10);
                    double dim2 = random.nextDouble() * 10 + random.nextDouble(10);
                    double dim3 = random.nextDouble() * 10 + random.nextDouble(10);
                    result[i] = new Triangle(color, dim1, dim2, dim3);
                }
                case CIRCLE -> {
                    double dim1 = random.nextDouble() * 10 + random.nextDouble(10);
                    result[i] = new Circle(color, dim1);
                }
            }
        }
        return result;
    }
}