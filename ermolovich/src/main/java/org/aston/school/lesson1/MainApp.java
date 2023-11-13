package org.aston.school.lesson1;

public class MainApp {
    public static void main(String[] args) {
        // System.out.println(isBetween10_20(11, 5)); //ex.1
        isPositiveOrNot(-12);                     //ex.2
      //  System.out.println(isPositive(11));       //ex.3

    }

    //ex.1
    private static boolean isBetween10_20(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    //ex.2
    private static void isPositiveOrNot(int a) {
        // System.out.println(a >= 0 ? "Положительное число" : "Отрицательное число");
        if (a >= 0) {
            System.out.println("Положительное число");
        } else {
            System.out.println("Отрицательное число");
        }
    }

    //ex.3
    private static boolean isPositive(int a) {
        return a < 0;
    }
}
