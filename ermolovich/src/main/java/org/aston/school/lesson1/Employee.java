package org.aston.school.lesson1;

//ex.1
public class Employee {
    private String name;
    private String position;
    private int age;
    private int phoneNumber;
    private String email;
    private int salary;

    public Employee(String name, String position, int age, int phoneNumber, String email, int salary) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salary = salary;
    }

    public void info() {
        System.out.println("ФИО: " + name + "; Должность: " + position + "; Возраст: " +
                age + "; Номер телефона: " + phoneNumber + "; E-mail: " + email + "; Зарплата: " + salary);
    }

    public void person() {
        Employee[] person = new Employee[5];
        person[0] = new Employee("Иванов Иван Иванович", "директор", 45, 234156,
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
