package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ShapeView;

import java.util.Arrays;
import java.util.Comparator;

public class ShapeControllerTest {

    private static class MockShapeView extends ShapeView {
        int printShapesCount = 0;
        int printMessageCount = 0;
        int printTotalAreaCount = 0;
        int printTotalAreaByTypeCount = 0;

        String lastMessagePassed;
        Shape[] lastShapesPassed;
        double lastTotalAreaPassed = 0;
        String lastTypePassed;
        double lastTypeAreaPassed = 0;

        @Override
        public void printMessage(String message) {
            this.printMessageCount++;
            this.lastMessagePassed = message;
        }

        @Override
        public void printShapes(Drawable[] shapes) {
            this.printShapesCount++;
            this.lastShapesPassed = (Shape[]) shapes;
        }

        @Override
        public void printTotalArea(double totalArea) {
            this.printTotalAreaCount++;
            this.lastTotalAreaPassed = totalArea;
        }

        @Override
        public void printTotalAreaByType(String type, double area) {
            this.printTotalAreaByTypeCount++;
            this.lastTypePassed = type;
            this.lastTypeAreaPassed = area;
        }

        public void reset() {
            this.printShapesCount = 0;
            this.printMessageCount = 0;
            this.printTotalAreaCount = 0;
            this.printTotalAreaByTypeCount = 0;

            this.lastMessagePassed = null;
            this.lastShapesPassed = null;
            this.lastTotalAreaPassed = 0;
            this.lastTypePassed = null;
            this.lastTypeAreaPassed = 0;
        }
    }

    private MockShapeView view = new MockShapeView();
    private final Shape[] shapes =  new Shape[]{
            new Rectangle("blue", 12.0, 10.0),
            new Rectangle("red", 14.0, 10.0),
            new Circle("blue", 7.0),
            new Circle("yellow", 5.0),
            new Triangle("blue", 3, 5, 6),
            new Triangle("red", 9, 5, 6),
    };
    private ShapeController controller = new ShapeController(view, shapes);

    @BeforeEach
    public void resetView() {
        view.reset();
    }

    @Test
    void testDisplayShapes() {
        controller.displayShapes();
        assert view.lastMessagePassed == null;
        assert view.printShapesCount == 1;
        assert view.lastShapesPassed == shapes;
    }

    @Test
    void testPrintTotalArea() {
        controller.printTotalArea();
        assert view.printTotalAreaCount == 1;
        assert view.lastMessagePassed == null;
        double expectedTotalArea = 0;
        for (var s : shapes) {
            expectedTotalArea += s.calcArea();
        }
        assert view.lastTotalAreaPassed == expectedTotalArea;
    }

    @Test
    void testPrintTotalAreaByType_Rectangles() {
        double expectedTotalArea = 0;
        for (var s : shapes) {
            if (s instanceof Rectangle) {
                expectedTotalArea += s.calcArea();
            }
        }
        controller.printTotalAreaByType(Rectangle.class);
        assert view.lastMessagePassed == null;
        assert view.lastTypeAreaPassed == expectedTotalArea;
        assert view.printTotalAreaByTypeCount == 1;
        assert view.lastTypePassed.equals(Rectangle.class.getSimpleName());
    }

    @Test
    void testPrintTotalAreaByType_Triangles() {
        double expectedTotalArea = 0;
        for (var s : shapes) {
            if (s instanceof Triangle) {
                expectedTotalArea += s.calcArea();
            }
        }
        controller.printTotalAreaByType(Triangle.class);
        assert view.lastMessagePassed == null;
        assert view.lastTypeAreaPassed == expectedTotalArea;
        assert view.printTotalAreaByTypeCount == 1;
        assert view.lastTypePassed.equals(Triangle.class.getSimpleName());
    }

    @Test
    void testPrintTotalAreaByType_Circles() {
        double expectedTotalArea = 0;
        for (var s : shapes) {
            if (s instanceof Circle) {
                expectedTotalArea += s.calcArea();
            }
        }
        controller.printTotalAreaByType(Circle.class);
        assert view.lastMessagePassed == null;
        assert view.lastTypeAreaPassed == expectedTotalArea;
        assert view.printTotalAreaByTypeCount == 1;
        assert view.lastTypePassed.equals(Circle.class.getSimpleName());
    }

    @Test
    void testSortByArea() {
        controller.sortByArea();
        assert view.printMessageCount == 1;
        assert view.lastMessagePassed.equals("Sorted by Area");
        assert view.printShapesCount == 1;
        var sorted = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(sorted, Comparator.comparing(Shape::calcArea));
        assert Arrays.equals(view.lastShapesPassed, sorted);
    }

    @Test
    void testSortByColor() {
        controller.sortByColor();
        assert view.printMessageCount == 1;
        assert view.lastMessagePassed.equals("Sorted by Color");
        assert view.printShapesCount == 1;
        var sorted = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(sorted, Comparator.comparing(Shape::getShapeColor));
        assert Arrays.equals(view.lastShapesPassed, sorted);
    }

}
