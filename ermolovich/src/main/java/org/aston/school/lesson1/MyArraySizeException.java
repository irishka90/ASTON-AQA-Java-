package org.aston.school.lesson1;

public class MyArraySizeException extends Exception {

    public MyArraySizeException() {
        super("Неверная размерность массива, ожидается 4 на 4");
    }
}
