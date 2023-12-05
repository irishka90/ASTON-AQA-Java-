package org.aston.school.lesson1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private ArrayList<T> fruits = new ArrayList<>();

    public void add(T item) {
        fruits.add(item);
    }


    public float getWeight() {   // Получаем вес коробки
        return fruits.stream()    // Преобразовываем  в поток
                .map(w -> w.getWeight())  // Преобразовываем в поток веса каждого фрукта
                .reduce(0f, Float::sum);  // Находим вес коробки
    }

    public boolean compare(Box<? extends Fruit> box) {  // Для сравнения коробок
        return this.getWeight() == box.getWeight();
    }

    public void addAll(List<T> fruits) {   // Добавить множество фруктов в коробку
        this.fruits.addAll(fruits);
    }

    public void transfer(Box<T> box) {
        box.addAll(this.fruits);  // Пересыпаем в другую коробку
        this.fruits.clear();  // очищаем текущую
    }

    @Override
    public String toString() {
        return "fruits=" + fruits;
    }
}
