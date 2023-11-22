package org.aston.school.lesson1;

abstract class Figure implements Figures {
    protected int a;
    protected String fillingColor;
    protected String borderColor;


    public Figure(int a, String fillingColor, String borderColor) {
        this.a = a;
        this.borderColor = borderColor;
        this.fillingColor = fillingColor;

    }

    @Override
    public String toString() {
        double sq = square();
        return Double.isNaN(sq) ? "Не возможно построить такой треугольник" :
                "[ Периметр: " + perimeter() + ", площадь: " + sq + ", цвет фона: " + fillingColor + "," +
                        " цвет границ: " + borderColor + "]";
    }
}
