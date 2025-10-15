package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    @Test
    void testCalcArea() {
        double width = 10.0;
        double height = -15.5;
        double expected = 155;
        Rectangle r = new Rectangle("blue", width, height);
        assertEquals(expected, r.calcArea(), 0.001);
    }

    @Test
    void testToString() {
        Rectangle r = new Rectangle("blue", 10.0, 15.5);
        assert r.toString().contains("blue");
        assert r.toString().contains("10.0");
        assert r.toString().contains("15.5");
        assert r.toString().contains("155");
    }
}
