package org.aston.school.lesson1;

public class MainApp {
    public static void main(String[] args) {


    }

    //ex.1
    public class Employee {
        private String name;
        private String position;
        private int age;
        private long phoneNumber;
        private String email;
        private int salary;

        public Employee(String name, String position, int age, long phoneNumber, String email, int salary) {
            this.name = name;
            this.position = position;
            this.age = age;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.salary = salary;
        }

        public void info() {
            System.out.println("ФИО:" + name + "; Должность:" + position + "; Возраст:" +
                    age + "; Номер телефона:" + phoneNumber + "; E-mail:" + email + "; Зарплата" + salary);
        }
    }


}
