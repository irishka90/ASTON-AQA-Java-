package org.aston;

public class FactorialException extends Exception {
    public FactorialException() {
        super("Значение должно быть от 0 до 20");
    }
}