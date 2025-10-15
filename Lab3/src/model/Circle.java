package model;

public class Circle extends Shape {

    private final double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = Math.abs(radius);
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return super.toString() + ", radius: %.2f, area: %.2f".formatted(this.radius, calcArea());
    }
}
