package org.aston.school.lesson1;

import java.util.Random;

public class MainApp {
    public static void main(String[] args) {

        Random random = new Random();

        Box<Orange> box1 = new Box<>(); // Создаем коробку апельсинов
        for (int i = 0; i < random.nextInt(10); i++) { // Заполняем коробку
            box1.add(new Orange());                             // апельсинами
        }


        Box<Apple> box2 = new Box<>();
        for (int i = 0; i < random.nextInt(10); i++) {
            box2.add(new Apple());  // яблоками
        }

        Box<Apple> box3 = new Box<>();
        for (int i = 0; i < random.nextInt(10); i++) {
            box3.add(new Apple());
        }

        System.out.println("Коробка апельсинов: " + box1.getWeight() + " "
                + " Коробка яблок 1: " + box2.getWeight() + " Коробка яблок 2: "
                + box3.getWeight() + "  equals "
                + box2.compare(box3)); // Сравниваем коробку 2 и 3

        box2.transfer(box3);   // Пересыпаем яблоки из текущей коробки (2) в коробку 3

        System.out.println("Коробка апельсинов: " + box1.getWeight() + " "
                + " Коробка яблок 1: " + box2.getWeight() + " Коробка яблок 2: "
                + box3.getWeight());
        ;



    }



}
