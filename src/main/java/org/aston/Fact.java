package org.aston;

/// синглтон класс с функцией вычисления факториала
// Fact.getFactorial(6) так вызывать в других местах, в скобки число

public class Fact {

    public static int getFactorial(int f) {
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }
}
