package org.aston.school.lesson1;

import java.util.Arrays;

public class MainApp {
    //ex.1
    public static void main(String[] args) {
        Employee first = new Employee("Иванов Иван Иванович", "инженер",
                35, 7567890, "ivanov@mail.ru", 1200);
        first.info();

        person();

        atractions();


    }

    //ex.2
    public static void person() {
        Employee[] person = new Employee[5];
        person[0] = new Employee("Грибов Иван Иванович", "директор", 45, 234156,
                "director@mail.ru", 2500);
        person[1] = new Employee("Петров петр Петрович", "менеджер", 25, 3456789,
                "petrov@mail.ru", 1300);
        person[2] = new Employee("Сидоров Василий Иванович", "инженер", 34, 1234556,
                "sidorov@mail.ru", 1500);
        person[3] = new Employee("Иванова Анна Алексеевна", "менеджер по закупкам", 38, 34512388,
                "anya@mail.ru", 1100);
        person[4] = new Employee("Кошкина Тамара Викторовна", "маркетолог", 28, 8716543,
                "toma@mail.ru", 1150);

    }
//ex.3
    public static void atractions() {
        Park.Attraction[] atractions = new Park.Attraction[3];
        atractions[0] = new Park.Attraction("Super8; ", "12 - 15; ", 12);
        atractions[1] = new Park.Attraction("Bee; ", "12 - 18; ", 13);
        atractions[2] = new Park.Attraction("Train; ", "10 - 16; ", 10);

        Park park = new Park("Парк", atractions);
        System.out.println(park);

    }

}
