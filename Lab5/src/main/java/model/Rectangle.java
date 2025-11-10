package model;

public class Rectangle extends Shape {

    private final double width;
    private final double height;
    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = Math.abs(width);
        this.height = Math.abs(height);
    }

    @Override
    public double calcArea() {
        return width*height;
    }

    @Override
    public String toString() {
        return super.toString() + ", width: %.2f, height: %.2f, area: %.2f".formatted(this.width, this.height, calcArea());
    }
}
