package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircleTest {

    @Test
    void testCalcArea() {
        double r = -3.0;
        Circle c = new Circle("red", r);
        double expected = 9.0*Math.PI;
        assertEquals(expected, c.calcArea(), 0.001);
    }

    @Test
    void testToString() {
        Circle r = new Circle("blue", 10.0);
        assert r.toString().contains("blue");
        assert r.toString().contains("10.0");
        assert r.toString().contains("%.2f".formatted(r.calcArea()));
    }
}
