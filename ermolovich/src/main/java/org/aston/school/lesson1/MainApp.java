package org.aston.school.lesson1;

import java.util.Arrays;

public class MainApp {
    //ex.1
    public static void main(String[] args) {
        Employee first = new Employee("Иванов Иван Иванович", "инженер",
                35, 7567890, "ivanov@mail.ru", 1200);
        first.info();

        person();

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


}
