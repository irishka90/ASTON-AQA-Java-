package org.aston.school.lesson1;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        System.out.println(isBetween10_20(11, 5)); //ex.1

        manyStrings(2, "Hello");            //ex.2

        isPositiveOrNot(0);                     //ex.3


        System.out.println(isPositive(11));       //ex.4


        System.out.println(leapYear(2204));          //ex.5


        int[] arr = {1, 0, 0, 1, 0, 0, 1, 0};       //ex.6
        masiveChange(arr);

        masive100();                                 //ex.7

        int[] mas = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};  //ex.8
        less6Masiv(mas);

        tableMasiv(5);                  //ex.9

        lineMasiv(8, 1);    //ex.10


    }

    //ex.1 .Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах от 10 до 20
    //(включительно), если да – вернуть true, в противном случае – false.
    private static boolean isBetween10_20(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    //ex.2 Написать метод, которому в качестве аргументов передается строка и число,
    // метод должен отпечатать в консоль указанную строку, указанное количество раз;


    private static void manyStrings(int n, String b) {

        for (int i = 0; i < n; i++) {

            System.out.println(b);
        }

    }

    ////ex.3  Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
    //    //положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.

    private static void isPositiveOrNot(int a) {
        // System.out.println(a >= 0 ? "Положительное число" : "Отрицательное число");
        if (a >= 0) {
            System.out.println("Положительное число");
        } else {
            System.out.println("Отрицательное число");
        }
    }


    /////ex. 4
    // Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true,
    // если число отрицательное, и вернуть false если положительное.

    private static boolean isPositive(int a) {
        return a < 0;
    }


    //ex.5 Написать метод, который определяет, является ли год високосным, и возвращает boolean (високосный - true, не
    //високосный - false). Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    private static boolean leapYear(int a) {
        if (a % 4 == 0) {
            return a % 400 == 0 || a % 100 != 0;
        }
        return false;
    }

    //ex.6 Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и
    //условия заменить 0 на 1, 1 на 0;
    private static void masiveChange(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 1) {
                arr[i] = 0;
            } else {
                arr[i] = 1;
            }

        }
        System.out.println(Arrays.toString(arr));

    }

    //ex.7 Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 ... 100;
    private static void masive100() {
        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;

        }
        System.out.println(Arrays.toString(arr));


    }

    //ex.8 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    private static void less6Masiv(int[] mas) {

        for (int i = 0; i < mas.length; i++) {
            if (mas[i] < 6) {
                mas[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(mas));
    }

    //ex.9 Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью
    //цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей, если обе сложно).
    //Определить элементы одной из диагоналей можно по следующему принципу: индексы таких элементов равны, то
    //есть [0][0], [1][1], [2][2], ..., [n][n];
    private static void tableMasiv(int n) {
        int[][] table = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                if (i == j) {
                    table[i][j] = 1;

                }
                if (j == n - 1 - i) {
                    table[i][j] = 1;
                }
                System.out.print(table[i][j] + " ");
            }

            System.out.println();
        }
    }

    //ex.10 Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий одномерный массив типа int
    //длиной len, каждая ячейка которого равна initialValue.
    private static void lineMasiv(int len, int initialValue) {
        int[] lineMas = new int[len];
        for (int i = 0; i < len; i++) {
            lineMas[i] = initialValue;

        }

        System.out.println(Arrays.toString(lineMas));
    }


}
