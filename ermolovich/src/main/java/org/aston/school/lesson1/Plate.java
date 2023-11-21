package org.aston.school.lesson1;

public class Plate {
    private int value;

    public Plate(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addFood(int food) {         //добавление еды
        this.value += food;
    }

    public void eatFood(int food) {         //уменьшение еды
        this.value -= food;
    }

    @Override
    public String toString() {
        return "Plate: " + value;
    }
}

