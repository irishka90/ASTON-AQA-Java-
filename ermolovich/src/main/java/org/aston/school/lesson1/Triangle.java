package org.aston.school.lesson1;

public class Triangle extends Figure {
    private final int b;
    private final int c;

    public Triangle(int a, int b, int c, String borderColor, String fillingColor ) {
        super(a, borderColor, fillingColor);
        this.b = b;
        this.c = c;
    }


    @Override
    public double square() {
        double p = halfPerimeter();
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }

    private double halfPerimeter() {
        return perimeter() / 2;
    }
}
