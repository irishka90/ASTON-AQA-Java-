package org.aston.school.lesson1;

public class Animal {
    private String name;
    private int swim;
    private int run;

    public Animal(String name, int swim, int run) {
        this.name = name;
        this.swim = swim;
        this.run = run;
    }

    public class Cat extends Animal {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

    }


}
