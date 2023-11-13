package org.aston.school.lesson1;

public class MainApp {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
    }

    private static void printThreeWords() {
        System.out.print("Orange\nBanana\nApple\n");
    }

    // или 2й, более длинный вариант
    private static void printThreeWords1() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }


    private static void checkSumSign() {
        int a = 0;
        int b = -10;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    private static void printColor() {
        int value = 120;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 100) {
            System.out.println("Зеленый");
        } else {
            System.out.println("Желтый");

        }

    }

    private static void compareNumbers() {
        int a = 120;
        int b = 17;
        if (a >= b) {
            System.out.println("a>=b");
        } else {
            System.out.println("a<b");
        }
    }


}
