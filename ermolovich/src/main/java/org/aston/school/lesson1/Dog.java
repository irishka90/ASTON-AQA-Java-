package org.aston.school.lesson1;

public class Dog extends Animal {

    private static int count = 0;  //добавлен счетчик собак

    public Dog(String name) {
        super(name);
        count++;                 //увеличение счетчика на 1 при создании каждой новой собаки
        this.limitSwim = 10;   //по условию собака может плыть 10м, и бежать 500м
        this.limitRun = 500;
    }

    public static int getCount() {
        return count;
    }
  }
