import controller.ShapeController;
import model.Circle;
import model.Rectangle;
import model.Triangle;
import view.ShapeView;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view, 10);

        controller.displayShapes();
        controller.printTotalArea();
        controller.printTotalAreaByType(Circle.class);
        controller.printTotalAreaByType(Rectangle.class);
        controller.printTotalAreaByType(Triangle.class);
        controller.sortByArea();
        controller.sortByColor();
    }
}
