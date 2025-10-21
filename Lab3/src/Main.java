import controller.ShapeController;
import model.Circle;
import model.Rectangle;
import model.ShapeModel;
import model.Triangle;
import view.ShapeView;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ShapeView view = new ShapeView();
        ShapeModel model = new ShapeModel(10);
        ShapeController controller = new ShapeController(view, model);

        controller.displayShapes();
        controller.printTotalArea();
        controller.printTotalAreaByType(Circle.class);
        controller.printTotalAreaByType(Rectangle.class);
        controller.printTotalAreaByType(Triangle.class);
        controller.sortByArea();
        controller.sortByColor();
    }
}
