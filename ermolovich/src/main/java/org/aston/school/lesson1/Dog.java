package org.aston.school.lesson1;

public class Dog extends Animal {
    ;

    public Dog(String name) {
        super(name);
        this.limitSwim = 10;   //по условию собака может плыть 10м, и бежать 500м
        this.limitRun = 500;
    }

  }
