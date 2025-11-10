package controller;

import model.*;
import service.ShapeFileService;
import view.ShapeView;

import java.io.*;

public class ShapeController {

    private final ShapeModel model;
    private final ShapeView view;
    private final ShapeFileService fileService;

    public ShapeController(ShapeView view, ShapeModel model) {
        this.view = view;
        this.model = model;
        this.fileService = new ShapeFileService();
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

    public void saveDataToFile(String filePath) {
        try {
            fileService.saveShapesToFile(filePath, model.getShapes());
            view.printMessage("Saved shapes to file");
        } catch (IOException e) {
            view.printMessage("Error writing to file %s%n".formatted(filePath) + e);
        }
    }

    public void readDataFromFile(String filePath) {
        try {
            Shape[] shapes = fileService.readShapesFromFile(filePath);
            model.setShapes(shapes);
            view.printMessage("Shapes updated successfully from file");
        } catch (ClassNotFoundException e) {
            view.printMessage("Error reading shapes from file %s%n".formatted(filePath) + e.getMessage());
        } catch (FileNotFoundException e) {
            view.printMessage("File %s not found%n".formatted(filePath) +  e.getMessage());
        } catch (IOException e) {
            view.printMessage("Error reading file %s%n".formatted(filePath) + e.getMessage());
        }
    }
}
