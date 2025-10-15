package model;

public class Triangle extends Shape {
    private final double a;
    private final double b;
    private final double c;

    public Triangle(String color, double a, double b, double c) {
        super(color);
        this.a = Math.abs(a);
        this.b = Math.abs(b);
        this.c = Math.abs(c);
    }

    @Override
    public double calcArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    @Override
    public String toString() {
        return super.toString() + ", a: %.2f, b: %.2f, c: %.2f, area: %.2f".formatted(a, b, c, calcArea());
    }
}
