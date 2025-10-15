package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {

    @Test
    void testCalcArea_ValidInput() {
        double a = 3.0;
        double b = -4.0;
        double c = 5.0;
        double expected = 0.5*3*4;
        Triangle t = new Triangle("white", a, b, c);
        assertEquals(expected, t.calcArea(), 0.001);
    }

    @Test
    void testToString() {
        Triangle r = new Triangle("blue", 10.0, 15.5, 12.0);
        assert r.toString().contains("blue");
        assert r.toString().contains("10.0");
        assert r.toString().contains("15.5");
        assert r.toString().contains("12.0");
        assert r.toString().contains("%.2f".formatted((r.calcArea())));
    }
}
