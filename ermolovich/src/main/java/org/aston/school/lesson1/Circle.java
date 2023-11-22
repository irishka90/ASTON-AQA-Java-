package org.aston.school.lesson1;

public class Circle extends Figure {
    public Circle(int a, String borderColor, String fillingColor) {
        super(a, borderColor, fillingColor);
    }

    @Override
    public double square() {
        return Math.PI * Math.pow(a, 2);
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * a;
    }
}
