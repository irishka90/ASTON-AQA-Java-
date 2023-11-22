package org.aston.school.lesson1;

public class Rectangle extends Figure {
    private final int b;

    public Rectangle(int a, int b, String borderColor, String fillingColor) {
        super(a, borderColor, fillingColor);
        this.b = b;
    }

    @Override
    public double square() {
        return a * b;
    }

    @Override
    public double perimeter() {
        return 2 * (a + b);
    }
}
