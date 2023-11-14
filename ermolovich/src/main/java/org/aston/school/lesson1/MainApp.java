package org.aston.school.lesson1;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        // System.out.println(isBetween10_20(11, 5)); //ex.1
        isPositiveOrNot(-12);                     //ex.2
        //  System.out.println(isPositive(11));       //ex.3
        manyStrings(2, "Hello");              //ex.4
        // System.out.println(leapYear(2204));          //ex.5

        int[] arr = {1, 0, 0, 1, 0, 0, 1, 0};       //ex.6
        masiveChange(arr);

        masive100();                                 //ex.7

        int[] mas = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};  //ex.8
        less6Masiv(mas);

        tableMasiv(5);                  //ex.9

        lineMasiv(8, 1);    //ex.10


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

    //ex.4
    private static void manyStrings(int n, String b) {

        for (int i = 0; i < n; i++) {

            System.out.println(b);
        }

    }

    //ex.5
    private static boolean leapYear(int a) {
        if (a % 4 == 0) {
            return a % 400 == 0 || a % 100 != 0;
        }
        return false;
    }

    //ex.6
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

    //ex.7
    private static void masive100() {
        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;

        }
        System.out.println(Arrays.toString(arr));


    }

    //ex.8
    private static void less6Masiv(int[] mas) {

        for (int i = 0; i < mas.length; i++) {
            if (mas[i] < 6) {
                mas[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(mas));
    }

    //ex.9
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

    //ex.10
    private static void lineMasiv(int len, int initialValue) {
        int[] lineMas = new int[len];
        for (int i = 0; i < len; i++) {
            lineMas[i] = initialValue;

        }

        System.out.println(Arrays.toString(lineMas));
    }


}
