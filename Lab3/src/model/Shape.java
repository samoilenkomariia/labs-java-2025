package model;

public abstract class Shape implements Drawable {
    protected String shapeColor;
    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    public void draw() {
        System.out.println(this);
    }

    public abstract double calcArea();

    @Override
    public String toString() {
        return "%s, color: %s".formatted(this.getClass().getSimpleName(), this.shapeColor);
    }
}
