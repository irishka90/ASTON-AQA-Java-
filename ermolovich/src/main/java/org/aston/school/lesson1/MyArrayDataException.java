package org.aston.school.lesson1;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int i, int j) {
        super("Элемент массива в ячейке " + i + "," + j + " невозможно преобразовать в число");
    }
}
