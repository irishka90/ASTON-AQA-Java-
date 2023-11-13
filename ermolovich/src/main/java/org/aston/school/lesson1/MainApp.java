package org.aston.school.lesson1;

public class MainApp {
    public static void main(String[] args) {
        System.out.println(5);
    }

    private static boolean isBetween10_20(int a, int b) {
        if (a + b >= 10 && a + b <= 20) {
            return true
        } else {
            return false
        }
    }
}
