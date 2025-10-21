package controller;

import model.*;
import view.ShapeView;

public class ShapeController {

    private final ShapeModel model;
    private final ShapeView view;

    public ShapeController(ShapeView view, ShapeModel model) {
        this.view = view;
        this.model = model;
    }

    public void displayShapes() {
        view.printShapes(model.getShapes());
    }

    public void printTotalArea() {
        double total = model.calcTotalArea();
        view.printTotalArea(total);
    }

    public void printTotalAreaByType(Class<? extends Shape> shapeClass) {
        double total = model.calcTotalAreaByType(shapeClass);
        view.printTotalAreaByType(shapeClass.getSimpleName(), total);
    }

    public void sortByArea() {
        Shape[] copy = model.getShapesSortedByArea();
        view.printMessage("Sorted by Area");
        view.printShapes(copy);
    }

    public void sortByColor() {
        Shape[] copy = model.getShapesSortedByColor();
        view.printMessage("Sorted by Color");
        view.printShapes(copy);
    }
}
